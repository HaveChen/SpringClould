package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdClassType;
import java.util.List;

/**
 * 班级类型Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdClassTypeService extends IService<StdClassType> {


  /**
   * 查询班级类型列表
   *
   * @param stdClassType 班级类型
   * @return 班级类型集合
   */
  public List<StdClassType> selectStdClassTypeList(StdClassType stdClassType);

}
