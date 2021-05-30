package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdSubjectType;
import com.ruoyi.standard.mapper.StdSubjectTypeMapper;
import com.ruoyi.standard.service.IStdSubjectTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 科目类型Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdSubjectTypeServiceImpl extends
    ServiceImpl<StdSubjectTypeMapper, StdSubjectType> implements IStdSubjectTypeService {

  @Autowired
  private StdSubjectTypeMapper stdSubjectTypeMapper;


  /**
   * 查询科目类型列表
   *
   * @param stdSubjectType 科目类型
   * @return 科目类型
   */
  @Override
  public List<StdSubjectType> selectStdSubjectTypeList(StdSubjectType stdSubjectType) {
    return stdSubjectTypeMapper.selectStdSubjectTypeList(stdSubjectType);
  }


}
