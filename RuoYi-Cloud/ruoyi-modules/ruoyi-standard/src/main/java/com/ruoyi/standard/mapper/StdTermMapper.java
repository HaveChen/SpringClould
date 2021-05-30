package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdTerm;
import java.util.List;

/**
 * 学期Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdTermMapper extends BaseMapper<StdTerm> {

  /**
   * 查询学期列表
   *
   * @param stdTerm 学期
   * @return 学期集合
   */
  public List<StdTerm> selectStdTermList(StdTerm stdTerm);

}
