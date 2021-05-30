package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdTeacherTitle;
import java.util.List;

/**
 * 教师职称Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdTeacherTitleService extends IService<StdTeacherTitle> {


  /**
   * 查询教师职称列表
   *
   * @param stdTeacherTitle 教师职称
   * @return 教师职称集合
   */
  public List<StdTeacherTitle> selectStdTeacherTitleList(StdTeacherTitle stdTeacherTitle);

}
