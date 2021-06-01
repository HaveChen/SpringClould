package com.ruoyi.system.service.impl;

import java.util.List;
                                                                                import com.ruoyi.common.core.utils.DateUtils;
            import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.TenantInfoDetailMapper;
import com.ruoyi.system.domain.TenantInfoDetail;
import com.ruoyi.system.service.ITenantInfoDetailService;

/**
 * 租户明细Service业务层处理
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Service
public class TenantInfoDetailServiceImpl extends ServiceImpl<TenantInfoDetailMapper,TenantInfoDetail>
    implements ITenantInfoDetailService {

  @Autowired
  private TenantInfoDetailMapper tenantInfoDetailMapper;


  /**
   * 查询租户明细列表
   *
   * @param tenantInfoDetail 租户明细
   * @return 租户明细
   */
  @Override
  public List<TenantInfoDetail> selectTenantInfoDetailList(TenantInfoDetail tenantInfoDetail) {
    return tenantInfoDetailMapper.selectTenantInfoDetailList(tenantInfoDetail);
  }

}
