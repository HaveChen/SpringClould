package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExtensionField;
import java.util.List;

/**
 * 采集扩展维度Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExtensionFieldService extends IService<CltExtensionField> {


  /**
   * 查询采集扩展维度列表
   *
   * @param cltExtensionField 采集扩展维度
   * @return 采集扩展维度集合
   */
  public List<CltExtensionField> selectCltExtensionFieldList(CltExtensionField cltExtensionField);

}
