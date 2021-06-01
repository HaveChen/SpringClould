package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.TenantInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 多租户Service接口
 *
 * @author chenyou
 * @date 2021-06-01
 */
public interface ITenantInfoService extends IService<TenantInfo> {

  /**
   * 查询多租户列表
   *
   * @param tenantInfo 多租户
   * @return 多租户集合
   */
  List<TenantInfo> selectTenantInfoList(TenantInfo tenantInfo);


}
