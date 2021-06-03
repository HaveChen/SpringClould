package com.ruoyi.collection.service;

import java.util.List;

import com.ruoyi.collection.domain.CltCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 采集课程Service接口
 *
 * @author chenyou
 * @date 2021-02-26
 */
public interface ICltCourseService extends IService<CltCourse> {


  /**
   * 查询采集课程列表
   *
   * @param cltCourse 采集课程
   * @return 采集课程集合
   */
  public List<CltCourse> selectCltCourseList(CltCourse cltCourse);

}
