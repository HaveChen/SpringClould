package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdGradeLeader;
import java.util.List;

/**
 * 年级组长Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdGradeLeaderService extends IService<StdGradeLeader> {


  /**
   * 查询年级组长列表
   *
   * @param stdGradeLeader 年级组长
   * @return 年级组长集合
   */
  public List<StdGradeLeader> selectStdGradeLeaderList(StdGradeLeader stdGradeLeader);

  boolean saveGtadeLeader(StdGradeLeader stdGradeLeader);

  boolean updateGradeLeaderById(StdGradeLeader stdGradeLeader);
}
