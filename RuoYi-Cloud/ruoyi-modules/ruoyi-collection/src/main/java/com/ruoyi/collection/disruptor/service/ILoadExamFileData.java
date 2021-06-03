package com.ruoyi.collection.disruptor.service;

import com.ruoyi.collection.domain.CltProgress;
import java.util.List;

/*
 *chenyou
 *2020/8/14  10:50
 */
public interface ILoadExamFileData {

  List<CltProgress> getExamFileImportDatas(String examId);
}
