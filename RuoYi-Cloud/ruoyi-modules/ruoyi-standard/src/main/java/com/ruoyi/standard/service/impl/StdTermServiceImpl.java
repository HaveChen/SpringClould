package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdTerm;
import com.ruoyi.standard.mapper.StdTermMapper;
import com.ruoyi.standard.service.IStdTermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学期Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdTermServiceImpl extends
    ServiceImpl<StdTermMapper, StdTerm> implements IStdTermService {

  @Autowired
  private StdTermMapper stdTermMapper;


  /**
   * 查询学期列表
   *
   * @param stdTerm 学期
   * @return 学期
   */
  @Override
  public List<StdTerm> selectStdTermList(StdTerm stdTerm) {
    return stdTermMapper.selectStdTermList(stdTerm);
  }


}
