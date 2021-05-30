package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdSchoolYear;
import java.util.List;

/**
 * 学年Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdSchoolYearMapper extends BaseMapper<StdSchoolYear> {

  /**
   * 查询学年列表
   *
   * @param stdSchoolYear 学年
   * @return 学年集合
   */
  public List<StdSchoolYear> selectStdSchoolYearList(StdSchoolYear stdSchoolYear);

}
