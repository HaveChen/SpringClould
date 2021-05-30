package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdGraduate;
import java.util.List;

/**
 * 届别Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdGraduateMapper extends BaseMapper<StdGraduate> {

  /**
   * 查询届别列表
   *
   * @param stdGraduate 届别
   * @return 届别集合
   */
  public List<StdGraduate> selectStdGraduateList(StdGraduate stdGraduate);

}
