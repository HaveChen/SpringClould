package com.ruoyi.collection.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;

/*
 *chenyou
 *2020/8/14  14:36
 */
public class ExamFileImportFactory implements EventFactory<ExamFileImportEvent> {

  @Override
  public ExamFileImportEvent newInstance() {
    return new ExamFileImportEvent();
  }
}
