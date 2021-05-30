package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdSubjectType;
import java.util.List;

/**
 * 科目类型Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdSubjectTypeMapper extends BaseMapper<StdSubjectType> {

  /**
   * 查询科目类型列表
   *
   * @param stdSubjectType 科目类型
   * @return 科目类型集合
   */
  public List<StdSubjectType> selectStdSubjectTypeList(StdSubjectType stdSubjectType);

}
