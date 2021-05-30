package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdGrade;
import java.util.List;

/**
 * 年级Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdGradeService extends IService<StdGrade> {


  /**
   * 查询年级列表
   *
   * @param stdGrade 年级
   * @return 年级集合
   */
  public List<StdGrade> selectStdGradeList(StdGrade stdGrade);

}
