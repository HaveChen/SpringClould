package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltItemDetail;
import java.util.List;

/**
 * 细目信息采集Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltItemDetailService extends IService<CltItemDetail> {


  /**
   * 查询细目信息采集列表
   *
   * @param cltItemDetail 细目信息采集
   * @return 细目信息采集集合
   */
  public List<CltItemDetail> selectCltItemDetailList(CltItemDetail cltItemDetail);

}
