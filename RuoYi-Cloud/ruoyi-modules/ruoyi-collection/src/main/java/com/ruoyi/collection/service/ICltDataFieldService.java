package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltDataField;
import java.util.List;

/**
 * 数据采集类型Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltDataFieldService extends IService<CltDataField> {


  /**
   * 查询数据采集类型列表
   *
   * @param cltDataField 数据采集类型
   * @return 数据采集类型集合
   */
  public List<CltDataField> selectCltDataFieldList(CltDataField cltDataField);

}
