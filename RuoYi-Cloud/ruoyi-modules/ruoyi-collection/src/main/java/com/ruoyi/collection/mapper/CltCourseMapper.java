package com.ruoyi.collection.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ruoyi.collection.domain.CltCourse;

/**
 * 采集课程Mapper接口
 *
 * @author chenyou
 * @date 2021-02-26
 */
public interface CltCourseMapper extends BaseMapper<CltCourse> {

  /**
   * 查询采集课程列表
   *
   * @param cltCourse 采集课程
   * @return 采集课程集合
   */
  public List<CltCourse> selectCltCourseList(CltCourse cltCourse);

}
