package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.mapper.CltExamDataFieldMapper;
import com.ruoyi.collection.service.ICltExamDataFieldService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据采集类型--考试Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExamDataFieldServiceImpl extends
    ServiceImpl<CltExamDataFieldMapper, CltExamDataField> implements ICltExamDataFieldService {

  @Autowired
  private CltExamDataFieldMapper cltExamDataFieldMapper;


  /**
   * 查询数据采集类型--考试列表
   *
   * @param cltExamDataField 数据采集类型--考试
   * @return 数据采集类型--考试
   */
  @Override
  public List<CltExamDataField> selectCltExamDataFieldList(CltExamDataField cltExamDataField) {
    return cltExamDataFieldMapper.selectCltExamDataFieldList(cltExamDataField);
  }

  @Override
  public List<CltExamDataField> selectCltExamDataFillds(String examId, String importType) {
    QueryWrapper<CltExamDataField> q = new QueryWrapper<>();
    q.eq("exam_id", examId).eq("exam_data_type_code", importType).eq("status", 0);
    return list(q);
  }


}
