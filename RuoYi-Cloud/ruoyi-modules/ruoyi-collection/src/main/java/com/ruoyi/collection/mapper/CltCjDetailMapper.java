package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltCjDetail;
import java.util.List;

/**
 * 成绩数据采集Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltCjDetailMapper extends BaseMapper<CltCjDetail> {

  /**
   * 查询成绩数据采集列表
   *
   * @param cltCjDetail 成绩数据采集
   * @return 成绩数据采集集合
   */
  public List<CltCjDetail> selectCltCjDetailList(CltCjDetail cltCjDetail);

  int deleteCjByexamId(String examId);
}
