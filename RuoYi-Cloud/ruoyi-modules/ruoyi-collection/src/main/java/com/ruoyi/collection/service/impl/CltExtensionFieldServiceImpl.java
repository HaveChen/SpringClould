package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExtensionField;
import com.ruoyi.collection.mapper.CltExtensionFieldMapper;
import com.ruoyi.collection.service.ICltExtensionFieldService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采集扩展维度Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExtensionFieldServiceImpl extends
    ServiceImpl<CltExtensionFieldMapper, CltExtensionField> implements ICltExtensionFieldService {

  @Autowired
  private CltExtensionFieldMapper cltExtensionFieldMapper;


  /**
   * 查询采集扩展维度列表
   *
   * @param cltExtensionField 采集扩展维度
   * @return 采集扩展维度
   */
  @Override
  public List<CltExtensionField> selectCltExtensionFieldList(CltExtensionField cltExtensionField) {
    return cltExtensionFieldMapper.selectCltExtensionFieldList(cltExtensionField);
  }


}
