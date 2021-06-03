package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExamType;
import java.util.List;

/**
 * 考试类型Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExamTypeService extends IService<CltExamType> {


  /**
   * 查询考试类型列表
   *
   * @param cltExamType 考试类型
   * @return 考试类型集合
   */
  public List<CltExamType> selectCltExamTypeList(CltExamType cltExamType);

}
