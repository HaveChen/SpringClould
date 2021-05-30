package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdSubjectCombine;
import com.ruoyi.standard.mapper.StdSubjectCombineMapper;
import com.ruoyi.standard.service.IStdSubjectCombineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 科目组合类型Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdSubjectCombineServiceImpl extends
    ServiceImpl<StdSubjectCombineMapper, StdSubjectCombine> implements IStdSubjectCombineService {

  @Autowired
  private StdSubjectCombineMapper stdSubjectCombineMapper;


  /**
   * 查询科目组合类型列表
   *
   * @param stdSubjectCombine 科目组合类型
   * @return 科目组合类型
   */
  @Override
  public List<StdSubjectCombine> selectStdSubjectCombineList(StdSubjectCombine stdSubjectCombine) {
    return stdSubjectCombineMapper.selectStdSubjectCombineList(stdSubjectCombine);
  }

  @Override
  public List<StdSubjectCombine> loadStdSubjectCombine(Long projectId) {
    return stdSubjectCombineMapper.loadStdSubjectCombine(projectId);
  }


}
