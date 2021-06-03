package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.mapper.CltCourseMapper;
import com.ruoyi.collection.service.ICltCourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采集课程Service业务层处理
 *
 * @author chenyou
 * @date 2021-02-26
 */
@Service
public class CltCourseServiceImpl extends
    ServiceImpl<CltCourseMapper, CltCourse> implements ICltCourseService {

  @Autowired
  private CltCourseMapper cltCourseMapper;


  /**
   * 查询采集课程列表
   *
   * @param cltCourse 采集课程
   * @return 采集课程
   */
  @Override
  public List<CltCourse> selectCltCourseList(CltCourse cltCourse) {
    return cltCourseMapper.selectCltCourseList(cltCourse);
  }


}
