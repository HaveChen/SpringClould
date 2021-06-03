package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltDataField;
import com.ruoyi.collection.mapper.CltDataFieldMapper;
import com.ruoyi.collection.service.ICltDataFieldService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据采集类型Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltDataFieldServiceImpl extends
    ServiceImpl<CltDataFieldMapper, CltDataField> implements ICltDataFieldService {

  @Autowired
  private CltDataFieldMapper cltDataFieldMapper;


  /**
   * 查询数据采集类型列表
   *
   * @param cltDataField 数据采集类型
   * @return 数据采集类型
   */
  @Override
  public List<CltDataField> selectCltDataFieldList(CltDataField cltDataField) {
    return cltDataFieldMapper.selectCltDataFieldList(cltDataField);
  }


}
