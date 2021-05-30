package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdTeacher;
import java.util.List;

/**
 * 教师Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdTeacherMapper extends BaseMapper<StdTeacher> {

  /**
   * 查询教师列表
   *
   * @param stdTeacher 教师
   * @return 教师集合
   */
  public List<StdTeacher> selectStdTeacherList(StdTeacher stdTeacher);

}
