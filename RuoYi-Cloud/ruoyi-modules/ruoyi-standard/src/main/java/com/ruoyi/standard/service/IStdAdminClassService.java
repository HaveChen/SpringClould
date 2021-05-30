package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdAdminClass;
import java.util.List;

/**
 * 行政班Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdAdminClassService extends IService<StdAdminClass> {


  /**
   * 查询行政班列表
   *
   * @param stdAdminClass 行政班
   * @return 行政班集合
   */
  List<StdAdminClass> selectStdAdminClassList(StdAdminClass stdAdminClass);

  /**
   * 保存行政班信息
   */
  boolean saveAdminClass(StdAdminClass stdAdminClass);

  boolean updateAdminClassById(StdAdminClass stdAdminClass);
}
