package com.ruoyi.collection.disruptor.service.impl;

import com.ruoyi.collection.disruptor.service.IExamFileImportContext;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

/*
 *chenyou
 *2020/8/14  9:49
 */
public class ExamFileImportContext implements IExamFileImportContext {

  private List<CltProgress> fileImports;
  private LongAdder taskTotalNum = new LongAdder();
  private LongAdder completeTaskNum = new LongAdder();
  private ICltProgressService cltProgressService;
  private ICltTestpaperService cltTestpaperService;
  private Map<String, Object> serviceMap;

  public ExamFileImportContext(List<CltProgress> fileImports, Map<String, Object> serviceMap) {
    this.fileImports = fileImports;
    this.serviceMap = serviceMap;
    this.cltProgressService = (ICltProgressService) serviceMap.get("cltProgressService");
    this.cltTestpaperService = (ICltTestpaperService) serviceMap.get("cltTestpaperService");
  }

  @Override
  public Map<String, Object> getServerMap() {
    return serviceMap;
  }

  @Override
  public ICltProgressService getCltProcessService() {
    return cltProgressService;
  }

  @Override
  public ICltTestpaperService getCltTestpaperService() {
    return cltTestpaperService;
  }

  @Override
  public List<CltProgress> getCltProcess() {
    return fileImports;
  }

  @Override
  public Long getTaskTotalNum() {
    return taskTotalNum.sum();
  }

  @Override
  public void setTaskTotalNum(int taskTotalNum) {
    this.taskTotalNum.add(taskTotalNum);
  }

  @Override
  public Long getCompleteNum() {
    return completeTaskNum.longValue();
  }

  @Override
  public void completeTask() {
    completeTaskNum.increment();
  }

  @Override
  public void clearCompleteTask() {
    completeTaskNum.reset();
  }
}
