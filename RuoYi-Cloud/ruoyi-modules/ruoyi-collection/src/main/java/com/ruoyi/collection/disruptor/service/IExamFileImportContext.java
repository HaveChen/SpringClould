package com.ruoyi.collection.disruptor.service;

import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
import java.util.List;
import java.util.Map;

/*
 *chenyou
 *2020/8/14  9:44
 */
public interface IExamFileImportContext {

  Map<String, Object> getServerMap();

  ICltProgressService getCltProcessService();
  
  ICltTestpaperService getCltTestpaperService();

  List<CltProgress> getCltProcess();

  /**
   * 线程总数
   */
  Long getTaskTotalNum();

  void setTaskTotalNum(int taskTotalNum);

  /**
   * 线程提交数
   */
  Long getCompleteNum();

  /**
   * 提交线程
   */
  void completeTask();

  /**
   * 初始化线程数
   */
  void clearCompleteTask();
}
