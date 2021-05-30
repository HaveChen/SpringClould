package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdGradeLeader;
import com.ruoyi.standard.mapper.StdGradeLeaderMapper;
import com.ruoyi.standard.service.IStdGradeLeaderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 年级组长Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdGradeLeaderServiceImpl extends
    ServiceImpl<StdGradeLeaderMapper, StdGradeLeader> implements IStdGradeLeaderService {

  @Autowired
  private StdGradeLeaderMapper stdGradeLeaderMapper;


  /**
   * 查询年级组长列表
   *
   * @param stdGradeLeader 年级组长
   * @return 年级组长
   */
  @Override
  public List<StdGradeLeader> selectStdGradeLeaderList(StdGradeLeader stdGradeLeader) {
    return stdGradeLeaderMapper.selectStdGradeLeaderList(stdGradeLeader);
  }

  @Override
  public boolean saveGtadeLeader(StdGradeLeader stdGradeLeader) {
    return save(stdGradeLeader);
  }

  @Override
  public boolean updateGradeLeaderById(StdGradeLeader stdGradeLeader) {
    return updateById(stdGradeLeader);
  }


}
