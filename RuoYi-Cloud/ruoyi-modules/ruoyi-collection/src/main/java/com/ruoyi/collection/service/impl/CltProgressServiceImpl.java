package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.bacth.utils.ExcelReaderHandler;
import com.ruoyi.collection.bacth.utils.ExcelReaderUtils;
import com.ruoyi.collection.disruptor.config.ExamFileImportConfig;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.UploadParams;
import com.ruoyi.collection.mapper.CltProgressMapper;
import com.ruoyi.collection.service.ICltExamDataFieldService;
import com.ruoyi.collection.service.ICltExamInfoService;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
import com.ruoyi.collection.util.ExectorCollectUtil;
import com.ruoyi.common.core.exception.UtilException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采集进度Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Slf4j
@Service
public class CltProgressServiceImpl extends
    ServiceImpl<CltProgressMapper, CltProgress> implements ICltProgressService {

  @Autowired
  private CltProgressMapper cltProgressMapper;
  @Autowired
  private ICltExamDataFieldService cltExamDataFieldService;
  @Autowired
  private ICltExamInfoService cltExamInfoService;
  @Autowired
  private ICltProgressService cltProgressService;
  @Autowired
  private ICltTestpaperService cltTestpaperService;
//  @Autowired
//  private Job importStudentJob;
//  @Autowired
//  private Job importCjJob;
//  @Autowired
//  private Job importCourseJob;
  @Autowired
  private JobLauncher jobLauncher;

  /**
   * 查询采集进度列表
   *
   * @param cltProgress 采集进度
   * @return 采集进度
   */
  @Override
  public List<CltProgress> selectCltProgressList(CltProgress cltProgress) {
    return cltProgressMapper.selectCltProgressList(cltProgress);
  }

  @Override
  public List<CltProgress> getExamFileImportsByExamId(String examId,
      String importStatus) {
    QueryWrapper<CltProgress> q = new QueryWrapper<>();
    q.eq("exam_id", examId).eq("import_status", importStatus);
    return list(q);
  }

  @Override
  public boolean initFile(MultipartFile multipartFile, UploadParams params) {
    try {
      boolean isZIP = false;
      String multipartFileName = multipartFile.getResource().getFilename();
      String fileType = FileTypeUtils.getFileType(multipartFileName);
      //先上传文件到指定目录
//      String filePath = RuoYiConfig.getProfile() + File.separator + FileUploadUtils
//          .upload(RuoYiConfig.getProfile(), multipartFile);
      String filePath="";
      List<File> files = new ArrayList<>();
      //是否是压缩文件
      if (StringUtils.isCompressFile(fileType)) {
        log.info("正在处理压缩文件。");
        files = analysisCpmpressFile(filePath, fileType);
        isZIP = true;
      } else {
        File file = new File(filePath);
        files.add(file);
      }
      for (File file : files) {
        //TODO 暂时不支持zip包含除XLSX和XLS以外的文件，包括多层压缩文件，待完善
        filePath = file.getPath();
        String fileName = file.getName();
        if (!isZIP) {
          fileName = multipartFileName;
        }
        fileType = FileTypeUtils.getFileType(fileName);
        if (!("XLSX".equalsIgnoreCase(fileType) || ".XLS".equalsIgnoreCase(fileType))) {
          if (isZIP) {
            log.warn("压缩文件{}，内部文件暂时不支持除Excel之外的其他格式文件：{}。", multipartFileName, fileName);
          } else {
            log.warn("上传文件：{}，不支持除Excel之外的其他文件。", multipartFileName);
          }
          continue;
        }
        //根据文件名称解析文件类型
        String importType = ExcelReaderUtils.analysisFileTypeByName(fileName);
        CltProgress progress = new CltProgress(fileName, filePath);
        progress.setFileType(fileType);
        if (StringUtils.isNotEmpty(importType)) {
          params.setImportType(importType);
        } else {
          //TODO 进一步确定,用表第一列匹配
        }
        //如果文件是xmb或者cj类型，处理识别对应科目
        if ("cj".equalsIgnoreCase(params.getImportType()) || "xmb"
            .equalsIgnoreCase(params.getImportType())) {
          List subjects = null;
          String subjectCode = ExcelReaderUtils
              .analysisFileMapSubjectCode(subjects, fileName);
          progress.setSubjectCode(subjectCode);
        }
        progress.setExamId(Long.parseLong(params.getExamId()));
        progress.setImportType(params.getImportType());
        //查询对应配置
        List<CltExamDataField> clts = cltExamDataFieldService
            .selectCltExamDataFillds(params.getExamId(), params.getImportType());
        //解析文件
        ExcelReaderHandler excel = new ExcelReaderHandler();
        excel.initFileMessage(progress, clts);
        //保存文件信息
        save(progress);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new UtilException("上传初始化文件失败.", e);
    }
    return true;
  }

  private List<File> analysisCpmpressFile(String filePath, String fileType) throws Exception {
    return FileUtils.unZip(filePath);
  }

  @Override
  public String importData(Long examId, String importFileIds) {
    try {
      //初始化需要的Bean
      Map<String, Object> serviceMap = new HashedMap<>();
      initServiceMap(serviceMap);
      ExamFileImportConfig t = new ExamFileImportConfig(examId + "", serviceMap);
      //TODO 处理重复提交
      ExectorCollectUtil.jobNewInstance().execute(t);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("导入失败：", e);
    }
    //TODO
    return null;
  }

  /**
   * 初始化ServiceMap
   */
  private void initServiceMap(Map<String, Object> serviceMap) {
    serviceMap.put("cltExamInfoService", cltExamInfoService);
    serviceMap.put("cltProgressService", cltProgressService);
    serviceMap.put("cltTestpaperService", cltTestpaperService);
//    serviceMap.put("importStudentJob", importStudentJob);
//    serviceMap.put("importCjJob", importCjJob);
//    serviceMap.put("importCourseJob", importCourseJob);
    serviceMap.put("jobLauncher", jobLauncher);
  }


  @Override
  public void updateImportStatus(Long progressId, String importType) {
    cltProgressMapper.updateImportStatus(progressId, importType);

  }

  @Override
  public void updateImportLine(Long progressId, int sucessLine, int errorLine) {
    CltProgress cltProgress = getById(progressId);
    cltProgress.setSucessLine(sucessLine + cltProgress.getSucessLine());
    cltProgress.setErrorLine(errorLine + cltProgress.getErrorLine());
    updateById(cltProgress);
  }
}
