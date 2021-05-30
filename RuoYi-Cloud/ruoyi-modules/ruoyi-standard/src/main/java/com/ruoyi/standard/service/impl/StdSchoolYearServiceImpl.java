package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdSchoolYear;
import com.ruoyi.standard.mapper.StdSchoolYearMapper;
import com.ruoyi.standard.service.IStdSchoolYearService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学年Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdSchoolYearServiceImpl extends
    ServiceImpl<StdSchoolYearMapper, StdSchoolYear> implements IStdSchoolYearService {

  @Autowired
  private StdSchoolYearMapper stdSchoolYearMapper;


  /**
   * 查询学年列表
   *
   * @param stdSchoolYear 学年
   * @return 学年
   */
  @Override
  public List<StdSchoolYear> selectStdSchoolYearList(StdSchoolYear stdSchoolYear) {
    return stdSchoolYearMapper.selectStdSchoolYearList(stdSchoolYear);
  }


}
