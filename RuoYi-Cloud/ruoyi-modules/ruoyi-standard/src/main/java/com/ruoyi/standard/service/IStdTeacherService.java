package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdTeacher;
import java.util.List;

/**
 * 教师Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdTeacherService extends IService<StdTeacher> {


  /**
   * 查询教师列表
   *
   * @param stdTeacher 教师
   * @return 教师集合
   */
  public List<StdTeacher> selectStdTeacherList(StdTeacher stdTeacher);

  boolean saveTeacher(StdTeacher stdTeacher);

  boolean updateTeacherById(StdTeacher stdTeacher);
}
