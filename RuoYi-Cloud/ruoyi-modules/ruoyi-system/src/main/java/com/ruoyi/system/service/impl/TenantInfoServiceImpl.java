package com.ruoyi.system.service.impl;

import java.util.List;
                                                                                import com.ruoyi.common.core.utils.DateUtils;
            import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.TenantInfoMapper;
import com.ruoyi.system.domain.TenantInfo;
import com.ruoyi.system.service.ITenantInfoService;

/**
 * 多租户Service业务层处理
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper,TenantInfo>
    implements ITenantInfoService {

  @Autowired
  private TenantInfoMapper tenantInfoMapper;


  /**
   * 查询多租户列表
   *
   * @param tenantInfo 多租户
   * @return 多租户
   */
  @Override
  public List<TenantInfo> selectTenantInfoList(TenantInfo tenantInfo) {
    return tenantInfoMapper.selectTenantInfoList(tenantInfo);
  }

}
