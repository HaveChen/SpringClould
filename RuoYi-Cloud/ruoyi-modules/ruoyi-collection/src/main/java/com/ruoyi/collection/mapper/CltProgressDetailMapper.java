package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltProgressDetail;
import java.util.List;

/**
 * 采集错误明细Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltProgressDetailMapper extends BaseMapper<CltProgressDetail> {

  /**
   * 查询采集错误明细列表
   *
   * @param cltProgressDetail 采集错误明细
   * @return 采集错误明细集合
   */
  public List<CltProgressDetail> selectCltProgressDetailList(CltProgressDetail cltProgressDetail);

}
