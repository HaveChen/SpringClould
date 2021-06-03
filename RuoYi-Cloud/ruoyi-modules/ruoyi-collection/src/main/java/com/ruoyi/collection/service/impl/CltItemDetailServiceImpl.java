package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltItemDetail;
import com.ruoyi.collection.mapper.CltItemDetailMapper;
import com.ruoyi.collection.service.ICltItemDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 细目信息采集Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltItemDetailServiceImpl extends
    ServiceImpl<CltItemDetailMapper, CltItemDetail> implements ICltItemDetailService {

  @Autowired
  private CltItemDetailMapper cltItemDetailMapper;


  /**
   * 查询细目信息采集列表
   *
   * @param cltItemDetail 细目信息采集
   * @return 细目信息采集
   */
  @Override
  public List<CltItemDetail> selectCltItemDetailList(CltItemDetail cltItemDetail) {
    return cltItemDetailMapper.selectCltItemDetailList(cltItemDetail);
  }


}
