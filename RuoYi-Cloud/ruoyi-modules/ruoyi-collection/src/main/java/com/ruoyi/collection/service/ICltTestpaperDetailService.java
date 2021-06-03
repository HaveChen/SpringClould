package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltTestpaperDetail;
import java.util.List;

/**
 * 试卷详情Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltTestpaperDetailService extends IService<CltTestpaperDetail> {


  /**
   * 查询试卷详情列表
   *
   * @param cltTestpaperDetail 试卷详情
   * @return 试卷详情集合
   */
  public List<CltTestpaperDetail> selectCltTestpaperDetailList(
      CltTestpaperDetail cltTestpaperDetail);

  List<CltTestpaperDetail> getListByTestPaperId(Long testpaperId);
}
