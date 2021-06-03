package com.ruoyi.collection.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;
import com.ruoyi.collection.disruptor.factory.ImportFileFactory;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltTestpaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/*
 * 考试数据导入事件 2020/8/14 10:07
 */
@Slf4j
@Configuration
public class ExamFileImportHandler
    implements EventHandler<ExamFileImportEvent>, WorkHandler<ExamFileImportEvent> {

  @Override
  public void onEvent(ExamFileImportEvent event, long sequence, boolean endOfBatch)
      throws Exception {
    this.onEvent(event);
  }

  @Override
  public void onEvent(ExamFileImportEvent event) {

    CltProgress fileImport = null;
    try {
      log.debug("文件：'{}'开始导入了... ...", event.getFileImport().getFileName());
      fileImport = event.getFileImport();
      // 设置状态正在导入
      event.getCltProgressService().updateImportStatus(fileImport.getProgressId(), "2");
      ImportFileFactory importFileFactory = event.getImportFileFactory();
      importFileFactory.importExamFile(event);
      updateTestpaperData(event, fileImport);
      log.info("文件{}-->导入成功.", event.getFileImport().getFileName());
    } catch (Exception e) {
      // 导入失败
      log.debug("文件{}-->导入失败:{}", event.getFileImport().getFileName(), e.getMessage());
      throw new RuntimeException("文件:" + fileImport.getFileName() + "-->导入失败.", e);
    } finally {
      event.getCltProgressService().updateImportStatus(fileImport.getProgressId(), "4");
    }
  }

  /**
   * 导入成功时，更新试卷相关数据
   */
  private void updateTestpaperData(ExamFileImportEvent event, CltProgress fileImport) {
    if ("cj".equals(fileImport.getImportType())) {
      CltTestpaper testpaper = event.getCltTestpaperService().getTestPaperByExamIdAndSubjectCode(
          fileImport.getExamId().toString(), fileImport.getSubjectCode());
      if (testpaper != null && testpaper.getHasCj() == 1) {
        testpaper.setHasCj(0L);
        event.getCltTestpaperService().updateById(testpaper);
      }
    }
  }

}
