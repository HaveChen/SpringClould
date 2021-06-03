package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltProgressDetail;
import com.ruoyi.collection.mapper.CltProgressDetailMapper;
import com.ruoyi.collection.service.ICltProgressDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采集错误明细Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltProgressDetailServiceImpl extends
    ServiceImpl<CltProgressDetailMapper, CltProgressDetail> implements ICltProgressDetailService {

  @Autowired
  private CltProgressDetailMapper cltProgressDetailMapper;


  /**
   * 查询采集错误明细列表
   *
   * @param cltProgressDetail 采集错误明细
   * @return 采集错误明细
   */
  @Override
  public List<CltProgressDetail> selectCltProgressDetailList(CltProgressDetail cltProgressDetail) {
    return cltProgressDetailMapper.selectCltProgressDetailList(cltProgressDetail);
  }


}
