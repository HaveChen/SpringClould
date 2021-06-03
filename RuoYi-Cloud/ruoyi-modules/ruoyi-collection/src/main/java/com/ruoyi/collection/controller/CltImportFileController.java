package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.UploadParams;
import com.ruoyi.collection.service.ICltExamInfoService;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 考试信息Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "数据采集")
@RestController
@RequestMapping("/importFile")
@Slf4j
public class CltImportFileController extends BaseController {

  @Autowired
  private ICltProgressService cltProgressService;



  @Log(title = "数据上传", businessType = BusinessType.IMPORT)
  @PostMapping("/uploadFiles")
  public AjaxResult uploadFiles(MultipartFile file, String importType, String examId) {
    UploadParams params = new UploadParams();
    params.setExamId(examId);
    params.setImportType(importType);
    cltProgressService.initFile(file, params);
    log.info("文件：{}上传完成。", file.getResource().getFilename());
    return AjaxResult.success();
  }

  /**
   * 导入数据
   *
   * @param examId 考试Id
   * @param importFileIds 采集文件
   */
  @Log(title = "数据导入", businessType = BusinessType.IMPORT)
  @GetMapping("/importData/{examId}/{importFileIds}")
  public AjaxResult importData(@PathVariable Long examId, @PathVariable String importFileIds) {
    log.info("开始导入考试：{}的{}数据了。", examId, importFileIds);
    cltProgressService.importData(examId, importFileIds);
    return AjaxResult.success();
  }
}
