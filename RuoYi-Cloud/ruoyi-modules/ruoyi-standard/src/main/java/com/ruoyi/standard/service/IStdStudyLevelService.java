package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdStudyLevel;
import java.util.List;

/**
 * 学段Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdStudyLevelService extends IService<StdStudyLevel> {


  /**
   * 查询学段列表
   *
   * @param stdStudyLevel 学段
   * @return 学段集合
   */
  public List<StdStudyLevel> selectStdStudyLevelList(StdStudyLevel stdStudyLevel);

}
