package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdStudyLevel;
import java.util.List;

/**
 * 学段Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdStudyLevelMapper extends BaseMapper<StdStudyLevel> {

  /**
   * 查询学段列表
   *
   * @param stdStudyLevel 学段
   * @return 学段集合
   */
  public List<StdStudyLevel> selectStdStudyLevelList(StdStudyLevel stdStudyLevel);

}
