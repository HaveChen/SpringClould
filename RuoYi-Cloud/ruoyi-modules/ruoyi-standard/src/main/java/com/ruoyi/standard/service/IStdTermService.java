package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdTerm;
import java.util.List;

/**
 * 学期Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdTermService extends IService<StdTerm> {


  /**
   * 查询学期列表
   *
   * @param stdTerm 学期
   * @return 学期集合
   */
  public List<StdTerm> selectStdTermList(StdTerm stdTerm);

}
