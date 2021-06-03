package com.ruoyi.collection.disruptor.event;

import com.ruoyi.collection.disruptor.factory.ImportFileFactory;
import com.ruoyi.collection.disruptor.service.IExamFileImportContext;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
import lombok.Data;

/*
 * 文件导入事件(任务)
 */
@Data
public class ExamFileImportEvent {

  private IExamFileImportContext importContext;
  private CltProgress fileImport;
  private ImportFileFactory importFileFactory;
  private ICltProgressService cltProgressService;
  private ICltTestpaperService cltTestpaperService;

  public void copyEvent(ExamFileImportEvent event) {
    this.importContext = event.importContext;
    this.fileImport = event.fileImport;
    this.importFileFactory = event.getImportFileFactory();
    this.cltProgressService = event.cltProgressService;
    this.cltTestpaperService = event.cltTestpaperService;
  }
}
