package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltTestpaperDetail;
import com.ruoyi.collection.mapper.CltTestpaperDetailMapper;
import com.ruoyi.collection.service.ICltTestpaperDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 试卷详情Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltTestpaperDetailServiceImpl extends
    ServiceImpl<CltTestpaperDetailMapper, CltTestpaperDetail> implements
    ICltTestpaperDetailService {

  @Autowired
  private CltTestpaperDetailMapper cltTestpaperDetailMapper;


  /**
   * 查询试卷详情列表
   *
   * @param cltTestpaperDetail 试卷详情
   * @return 试卷详情
   */
  @Override
  public List<CltTestpaperDetail> selectCltTestpaperDetailList(
      CltTestpaperDetail cltTestpaperDetail) {
    return cltTestpaperDetailMapper.selectCltTestpaperDetailList(cltTestpaperDetail);
  }

  @Override
  public List<CltTestpaperDetail> getListByTestPaperId(Long testpaperId) {
    QueryWrapper<CltTestpaperDetail> q = new QueryWrapper<>();
    q.eq("testpaper_id", testpaperId);
    return list(q);
  }


}
