package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdGrade;
import java.util.List;

/**
 * 年级Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdGradeMapper extends BaseMapper<StdGrade> {

  /**
   * 查询年级列表
   *
   * @param stdGrade 年级
   * @return 年级集合
   */
  public List<StdGrade> selectStdGradeList(StdGrade stdGrade);

}
