package com.ruoyi.collection.disruptor.produce;

import com.lmax.disruptor.dsl.Disruptor;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;
import com.ruoyi.collection.disruptor.factory.impl.ExcelFileImportFactory;
import com.ruoyi.collection.disruptor.service.IExamFileImportContext;
import com.ruoyi.collection.disruptor.translator.ExamFileImportTranslator;
import com.ruoyi.collection.domain.CltProgress;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
 *任务生产发布
 *2020/8/14  10:20
 */
public class ExamFileImportProduce implements Runnable {

  private IExamFileImportContext context;
  private List<CltProgress> fileImports;
  private CountDownLatch countDownLatch;
  private Disruptor<ExamFileImportEvent> disruptor;

  @Override
  public void run() {
    // 按照每个文件生成一个任务
    for (CltProgress fi : fileImports) {
      ExamFileImportEvent event = new ExamFileImportEvent();
      event.setFileImport(fi);
      event.setImportContext(context);
      event.setCltProgressService(context.getCltProcessService());
      event.setCltTestpaperService(context.getCltTestpaperService());
      event.setImportFileFactory(new ExcelFileImportFactory(context.getServerMap()));
      disruptor.publishEvent(new ExamFileImportTranslator(event));
    }
  }

  public ExamFileImportProduce(IExamFileImportContext examFileImportContext) {
    this.context = examFileImportContext;
    fileImports = context.getCltProcess();
    int size = fileImports.size();
    countDownLatch = new CountDownLatch(size);
    context.setTaskTotalNum(size);
  }

  public CountDownLatch getCountDownLatch() {
    return countDownLatch;
  }

  public void setDisruptor(Disruptor<ExamFileImportEvent> disruptor) {
    this.disruptor = disruptor;
  }
}
