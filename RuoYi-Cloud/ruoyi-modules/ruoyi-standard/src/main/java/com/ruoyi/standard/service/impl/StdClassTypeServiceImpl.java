package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdClassType;
import com.ruoyi.standard.mapper.StdClassTypeMapper;
import com.ruoyi.standard.service.IStdClassTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 班级类型Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdClassTypeServiceImpl extends
    ServiceImpl<StdClassTypeMapper, StdClassType> implements IStdClassTypeService {

  @Autowired
  private StdClassTypeMapper stdClassTypeMapper;


  /**
   * 查询班级类型列表
   *
   * @param stdClassType 班级类型
   * @return 班级类型
   */
  @Override
  public List<StdClassType> selectStdClassTypeList(StdClassType stdClassType) {
    return stdClassTypeMapper.selectStdClassTypeList(stdClassType);
  }


}
