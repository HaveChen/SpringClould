package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdAdminClass;
import java.util.List;

/**
 * 行政班Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdAdminClassMapper extends BaseMapper<StdAdminClass> {

  /**
   * 查询行政班列表
   *
   * @param stdAdminClass 行政班
   * @return 行政班集合
   */
  public List<StdAdminClass> selectStdAdminClassList(StdAdminClass stdAdminClass);

}
