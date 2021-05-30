package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdStudyLevel;
import com.ruoyi.standard.mapper.StdStudyLevelMapper;
import com.ruoyi.standard.service.IStdStudyLevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学段Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdStudyLevelServiceImpl extends
    ServiceImpl<StdStudyLevelMapper, StdStudyLevel> implements IStdStudyLevelService {

  @Autowired
  private StdStudyLevelMapper stdStudyLevelMapper;


  /**
   * 查询学段列表
   *
   * @param stdStudyLevel 学段
   * @return 学段
   */
  @Override
  public List<StdStudyLevel> selectStdStudyLevelList(StdStudyLevel stdStudyLevel) {
    return stdStudyLevelMapper.selectStdStudyLevelList(stdStudyLevel);
  }


}
