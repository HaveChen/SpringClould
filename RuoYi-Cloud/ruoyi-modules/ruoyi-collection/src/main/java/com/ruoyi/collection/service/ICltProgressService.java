package com.ruoyi.collection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.UploadParams;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采集进度Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltProgressService extends IService<CltProgress> {


  /**
   * 查询采集进度列表
   *
   * @param cltProgress 采集进度
   * @return 采集进度集合
   */
  public List<CltProgress> selectCltProgressList(CltProgress cltProgress);

  /**
   * 根据状态查询数据
   *
   * @param examId 考试Id
   * @param importStatus 状态:0匹配失败，1匹配成功，2等待导入3正在导入4导入完成
   * @return 进度列表
   */
  List<CltProgress> getExamFileImportsByExamId(String examId,
      String importStatus);

  /**
   * 上传初始化文件
   *
   * @param file 文件信息
   * @param params 上传参数
   */
  boolean initFile(MultipartFile file, UploadParams params);


  String importData(Long examId, String importType);

  /**
   * 根据文件导入状态
   */
  void updateImportStatus(Long progressId, String importType);

  /**
   *
   */
  void updateImportLine(Long progressId, int sucessLine, int errorLine);

}
