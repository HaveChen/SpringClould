package com.ruoyi.collection.disruptor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.disruptor.service.ILoadExamFileData;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltProgressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *chenyou
 *2020/8/14  10:54
 */
@Service
public class LoadExamFileDataImpl implements ILoadExamFileData {

  @Autowired
  private ICltProgressService cltProgressService;

  @Override
  public List<CltProgress> getExamFileImportDatas(String examId) {
    return cltProgressService.list(
        new QueryWrapper<CltProgress>().eq("exam_id", examId).eq("status", 0));
  }
}
