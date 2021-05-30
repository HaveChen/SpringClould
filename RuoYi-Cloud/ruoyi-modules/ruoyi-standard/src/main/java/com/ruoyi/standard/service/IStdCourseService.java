package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdCourse;
import java.util.List;

/**
 * 课程Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdCourseService extends IService<StdCourse> {


  /**
   * 查询课程列表
   *
   * @param stdCourse 课程
   * @return 课程集合
   */
  public List<StdCourse> selectStdCourseList(StdCourse stdCourse);

  boolean saveCourse(StdCourse stdCourse);

  boolean updateCourseById(StdCourse stdCourse);
}
