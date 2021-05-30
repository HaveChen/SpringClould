package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdTeachClass;
import java.util.List;

/**
 * 教学班Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdTeachClassService extends IService<StdTeachClass> {


  /**
   * 查询教学班列表
   *
   * @param stdTeachClass 教学班
   * @return 教学班集合
   */
  public List<StdTeachClass> selectStdTeachClassList(StdTeachClass stdTeachClass);

  boolean saveTeachClass(StdTeachClass stdTeachClass);

  boolean updateTeachClassById(StdTeachClass stdTeachClass);
}
