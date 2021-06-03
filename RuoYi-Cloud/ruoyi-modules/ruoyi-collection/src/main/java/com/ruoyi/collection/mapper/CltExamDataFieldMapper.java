package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltExamDataField;
import java.util.List;

/**
 * 数据采集类型--考试Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltExamDataFieldMapper extends BaseMapper<CltExamDataField> {

  /**
   * 查询数据采集类型--考试列表
   *
   * @param cltExamDataField 数据采集类型--考试
   * @return 数据采集类型--考试集合
   */
  public List<CltExamDataField> selectCltExamDataFieldList(CltExamDataField cltExamDataField);

  /**
   * 删除考试相关信息
   */
  void removeDataFieldByExamIds(List<Long> examIds);

}
