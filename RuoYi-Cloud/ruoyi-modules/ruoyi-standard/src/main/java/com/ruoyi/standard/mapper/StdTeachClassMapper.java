package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdTeachClass;
import java.util.List;

/**
 * 教学班Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdTeachClassMapper extends BaseMapper<StdTeachClass> {

  /**
   * 查询教学班列表
   *
   * @param stdTeachClass 教学班
   * @return 教学班集合
   */
  public List<StdTeachClass> selectStdTeachClassList(StdTeachClass stdTeachClass);

}
