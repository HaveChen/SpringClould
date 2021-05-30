package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdGraduate;
import java.util.List;

/**
 * 届别Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdGraduateService extends IService<StdGraduate> {


  /**
   * 查询届别列表
   *
   * @param stdGraduate 届别
   * @return 届别集合
   */
  public List<StdGraduate> selectStdGraduateList(StdGraduate stdGraduate);

}
