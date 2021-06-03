package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltExamType;
import java.util.List;

/**
 * 考试类型Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltExamTypeMapper extends BaseMapper<CltExamType> {

  /**
   * 查询考试类型列表
   *
   * @param cltExamType 考试类型
   * @return 考试类型集合
   */
  public List<CltExamType> selectCltExamTypeList(CltExamType cltExamType);

}
