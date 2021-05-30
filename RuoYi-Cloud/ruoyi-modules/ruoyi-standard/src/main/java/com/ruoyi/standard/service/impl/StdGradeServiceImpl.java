package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdGrade;
import com.ruoyi.standard.mapper.StdGradeMapper;
import com.ruoyi.standard.service.IStdGradeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 年级Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdGradeServiceImpl extends
    ServiceImpl<StdGradeMapper, StdGrade> implements IStdGradeService {

  @Autowired
  private StdGradeMapper stdGradeMapper;


  /**
   * 查询年级列表
   *
   * @param stdGrade 年级
   * @return 年级
   */
  @Override
  public List<StdGrade> selectStdGradeList(StdGrade stdGrade) {
    return stdGradeMapper.selectStdGradeList(stdGrade);
  }


}
