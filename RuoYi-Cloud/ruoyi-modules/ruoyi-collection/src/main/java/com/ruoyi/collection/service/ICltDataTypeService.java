package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltDataType;
import java.util.List;

/**
 * 数据采集类型Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltDataTypeService extends IService<CltDataType> {


  /**
   * 查询数据采集类型列表
   *
   * @param cltDataType 数据采集类型
   * @return 数据采集类型集合
   */
  public List<CltDataType> selectCltDataTypeList(CltDataType cltDataType);

}
