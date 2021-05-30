package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdCourse;
import java.util.List;

/**
 * 课程Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdCourseMapper extends BaseMapper<StdCourse> {

  /**
   * 查询课程列表
   *
   * @param stdCourse 课程
   * @return 课程集合
   */
  public List<StdCourse> selectStdCourseList(StdCourse stdCourse);

}
