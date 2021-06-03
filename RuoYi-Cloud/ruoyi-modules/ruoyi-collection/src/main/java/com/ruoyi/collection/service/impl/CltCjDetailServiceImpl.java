package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltCjDetail;
import com.ruoyi.collection.mapper.CltCjDetailMapper;
import com.ruoyi.collection.service.ICltCjDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 成绩数据采集Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltCjDetailServiceImpl extends
    ServiceImpl<CltCjDetailMapper, CltCjDetail> implements ICltCjDetailService {

  @Autowired
  private CltCjDetailMapper cltCjDetailMapper;


  /**
   * 查询成绩数据采集列表
   *
   * @param cltCjDetail 成绩数据采集
   * @return 成绩数据采集
   */
  @Override
  public List<CltCjDetail> selectCltCjDetailList(CltCjDetail cltCjDetail) {
    return cltCjDetailMapper.selectCltCjDetailList(cltCjDetail);
  }

  @Override
  public boolean deleteCjByexamId(String examId) {
    cltCjDetailMapper.deleteCjByexamId(examId);
    return true;
  }


}
