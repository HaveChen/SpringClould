package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdTeacherTitle;
import java.util.List;

/**
 * 教师职称Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdTeacherTitleMapper extends BaseMapper<StdTeacherTitle> {

  /**
   * 查询教师职称列表
   *
   * @param stdTeacherTitle 教师职称
   * @return 教师职称集合
   */
  public List<StdTeacherTitle> selectStdTeacherTitleList(StdTeacherTitle stdTeacherTitle);

}
