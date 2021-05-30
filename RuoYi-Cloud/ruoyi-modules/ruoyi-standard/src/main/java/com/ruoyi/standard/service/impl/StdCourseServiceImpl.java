package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdCourse;
import com.ruoyi.standard.mapper.StdCourseMapper;
import com.ruoyi.standard.service.IStdCourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdCourseServiceImpl extends
    ServiceImpl<StdCourseMapper, StdCourse> implements IStdCourseService {

  @Autowired
  private StdCourseMapper stdCourseMapper;


  /**
   * 查询课程列表
   *
   * @param stdCourse 课程
   * @return 课程
   */
  @Override
  public List<StdCourse> selectStdCourseList(StdCourse stdCourse) {
    return stdCourseMapper.selectStdCourseList(stdCourse);
  }

  @Override
  public boolean saveCourse(StdCourse stdCourse) {
    //stdCourse.setDeptCode(remoteSysDeptService.getDeptCodeById(stdCourse.getDeptId()));
    return save(stdCourse);
  }

  @Override
  public boolean updateCourseById(StdCourse stdCourse) {
    return updateById(stdCourse);
  }


}
