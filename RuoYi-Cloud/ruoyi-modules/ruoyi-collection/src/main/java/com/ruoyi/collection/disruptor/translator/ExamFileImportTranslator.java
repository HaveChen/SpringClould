package com.ruoyi.collection.disruptor.translator;

import com.lmax.disruptor.EventTranslator;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;

/*
 *chenyou
 *2020/8/14  14:48
 */
public class ExamFileImportTranslator implements EventTranslator<ExamFileImportEvent> {

  private ExamFileImportEvent examFileImportEvent;

  private ExamFileImportTranslator() {}

  public ExamFileImportTranslator(ExamFileImportEvent event) {
    this.examFileImportEvent = event;
  }

  @Override
  public void translateTo(ExamFileImportEvent event, long sequence) {
    event.copyEvent(examFileImportEvent);
  }
}
