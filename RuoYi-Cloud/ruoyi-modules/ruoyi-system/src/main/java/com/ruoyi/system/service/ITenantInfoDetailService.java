package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.TenantInfoDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租户明细Service接口
 *
 * @author chenyou
 * @date 2021-06-01
 */
public interface ITenantInfoDetailService extends IService<TenantInfoDetail> {

  /**
   * 查询租户明细列表
   *
   * @param tenantInfoDetail 租户明细
   * @return 租户明细集合
   */
  List<TenantInfoDetail> selectTenantInfoDetailList(TenantInfoDetail tenantInfoDetail);


}
