package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdSchoolYear;
import java.util.List;

/**
 * 学年Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdSchoolYearService extends IService<StdSchoolYear> {


  /**
   * 查询学年列表
   *
   * @param stdSchoolYear 学年
   * @return 学年集合
   */
  public List<StdSchoolYear> selectStdSchoolYearList(StdSchoolYear stdSchoolYear);

}
