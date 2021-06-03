package com.ruoyi.collection.disruptor.factory;

import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;

/*
 *chenyou
 *2020/8/14  15:21
 */
public interface ImportFileFactory {

  void importExamFile(ExamFileImportEvent fileImportEvent) throws Exception;
}
