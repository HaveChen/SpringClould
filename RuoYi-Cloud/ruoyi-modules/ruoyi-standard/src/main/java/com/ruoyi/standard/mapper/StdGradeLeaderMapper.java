package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdGradeLeader;
import java.util.List;

/**
 * 年级组长Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdGradeLeaderMapper extends BaseMapper<StdGradeLeader> {

  /**
   * 查询年级组长列表
   *
   * @param stdGradeLeader 年级组长
   * @return 年级组长集合
   */
  public List<StdGradeLeader> selectStdGradeLeaderList(StdGradeLeader stdGradeLeader);

}
