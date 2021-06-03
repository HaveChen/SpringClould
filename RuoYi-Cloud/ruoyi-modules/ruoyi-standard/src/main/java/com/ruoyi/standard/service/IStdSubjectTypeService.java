package com.ruoyi.standard.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.api.domain.StdSubjectType;
import java.util.List;

/**
 * 科目类型Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdSubjectTypeService extends IService<StdSubjectType> {


  /**
   * 查询科目类型列表
   *
   * @param stdSubjectType 科目类型
   * @return 科目类型集合
   */
  public List<StdSubjectType> selectStdSubjectTypeList(StdSubjectType stdSubjectType);

}
