package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.TenantInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 多租户Mapper接口
 *
 * @author chenyou
 * @date 2021-06-01
 */
public interface TenantInfoMapper extends BaseMapper<TenantInfo> {

  /**
   * 查询多租户列表
   *
   * @param tenantInfo 多租户
   * @return 多租户集合
   */
  List<TenantInfo> selectTenantInfoList(TenantInfo tenantInfo);

}
