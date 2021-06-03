package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltDataField;
import java.util.List;

/**
 * 数据采集类型Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltDataFieldMapper extends BaseMapper<CltDataField> {

  /**
   * 查询数据采集类型列表
   *
   * @param cltDataField 数据采集类型
   * @return 数据采集类型集合
   */
  public List<CltDataField> selectCltDataFieldList(CltDataField cltDataField);

}
