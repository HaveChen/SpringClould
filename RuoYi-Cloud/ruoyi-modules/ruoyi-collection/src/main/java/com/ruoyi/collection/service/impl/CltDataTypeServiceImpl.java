package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltDataType;
import com.ruoyi.collection.mapper.CltDataTypeMapper;
import com.ruoyi.collection.service.ICltDataTypeService;
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
public class CltDataTypeServiceImpl extends
    ServiceImpl<CltDataTypeMapper, CltDataType> implements ICltDataTypeService {

  @Autowired
  private CltDataTypeMapper cltDataTypeMapper;


  /**
   * 查询数据采集类型列表
   *
   * @param cltDataType 数据采集类型
   * @return 数据采集类型
   */
  @Override
  public List<CltDataType> selectCltDataTypeList(CltDataType cltDataType) {
    return cltDataTypeMapper.selectCltDataTypeList(cltDataType);
  }


}
