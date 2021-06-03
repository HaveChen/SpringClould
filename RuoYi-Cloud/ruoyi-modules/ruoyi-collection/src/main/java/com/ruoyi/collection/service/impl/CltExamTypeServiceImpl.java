package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExamType;
import com.ruoyi.collection.mapper.CltExamTypeMapper;
import com.ruoyi.collection.service.ICltExamTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 考试类型Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExamTypeServiceImpl extends
    ServiceImpl<CltExamTypeMapper, CltExamType> implements ICltExamTypeService {

  @Autowired
  private CltExamTypeMapper cltExamTypeMapper;


  /**
   * 查询考试类型列表
   *
   * @param cltExamType 考试类型
   * @return 考试类型
   */
  @Override
  public List<CltExamType> selectCltExamTypeList(CltExamType cltExamType) {
    return cltExamTypeMapper.selectCltExamTypeList(cltExamType);
  }


}
