package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdTeacher;
import com.ruoyi.standard.mapper.StdTeacherMapper;
import com.ruoyi.standard.service.IStdTeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdTeacherServiceImpl extends
    ServiceImpl<StdTeacherMapper, StdTeacher> implements IStdTeacherService {

  @Autowired
  private StdTeacherMapper stdTeacherMapper;


  /**
   * 查询教师列表
   *
   * @param stdTeacher 教师
   * @return 教师
   */
  @Override
  public List<StdTeacher> selectStdTeacherList(StdTeacher stdTeacher) {
    return stdTeacherMapper.selectStdTeacherList(stdTeacher);
  }

  @Override
  public boolean saveTeacher(StdTeacher stdTeacher) {
    return save(stdTeacher);
  }

  @Override
  public boolean updateTeacherById(StdTeacher stdTeacher) {
    return updateById(stdTeacher);
  }


}
