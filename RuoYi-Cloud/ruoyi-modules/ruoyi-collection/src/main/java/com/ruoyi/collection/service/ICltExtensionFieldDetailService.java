package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExtensionFieldDetail;
import java.util.List;

/**
 * 采集扩展维度明细Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExtensionFieldDetailService extends IService<CltExtensionFieldDetail> {


  /**
   * 查询采集扩展维度明细列表
   *
   * @param cltExtensionFieldDetail 采集扩展维度明细
   * @return 采集扩展维度明细集合
   */
  public List<CltExtensionFieldDetail> selectCltExtensionFieldDetailList(
      CltExtensionFieldDetail cltExtensionFieldDetail);

}
