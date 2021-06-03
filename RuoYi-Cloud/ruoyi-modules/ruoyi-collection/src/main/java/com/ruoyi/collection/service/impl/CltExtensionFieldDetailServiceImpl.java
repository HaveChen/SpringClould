package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExtensionFieldDetail;
import com.ruoyi.collection.mapper.CltExtensionFieldDetailMapper;
import com.ruoyi.collection.service.ICltExtensionFieldDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采集扩展维度明细Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExtensionFieldDetailServiceImpl extends
    ServiceImpl<CltExtensionFieldDetailMapper, CltExtensionFieldDetail> implements
    ICltExtensionFieldDetailService {

  @Autowired
  private CltExtensionFieldDetailMapper cltExtensionFieldDetailMapper;


  /**
   * 查询采集扩展维度明细列表
   *
   * @param cltExtensionFieldDetail 采集扩展维度明细
   * @return 采集扩展维度明细
   */
  @Override
  public List<CltExtensionFieldDetail> selectCltExtensionFieldDetailList(
      CltExtensionFieldDetail cltExtensionFieldDetail) {
    return cltExtensionFieldDetailMapper.selectCltExtensionFieldDetailList(cltExtensionFieldDetail);
  }


}
