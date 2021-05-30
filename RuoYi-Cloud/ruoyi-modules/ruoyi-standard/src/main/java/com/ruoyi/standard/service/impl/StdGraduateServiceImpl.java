package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdGraduate;
import com.ruoyi.standard.mapper.StdGraduateMapper;
import com.ruoyi.standard.service.IStdGraduateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 届别Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdGraduateServiceImpl extends
    ServiceImpl<StdGraduateMapper, StdGraduate> implements IStdGraduateService {

  @Autowired
  private StdGraduateMapper stdGraduateMapper;


  /**
   * 查询届别列表
   *
   * @param stdGraduate 届别
   * @return 届别
   */
  @Override
  public List<StdGraduate> selectStdGraduateList(StdGraduate stdGraduate) {
    return stdGraduateMapper.selectStdGraduateList(stdGraduate);
  }


}
