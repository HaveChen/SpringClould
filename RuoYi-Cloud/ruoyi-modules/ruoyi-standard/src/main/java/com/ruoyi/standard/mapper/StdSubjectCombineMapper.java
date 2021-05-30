package com.ruoyi.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdSubjectCombine;
import java.util.List;

/**
 * 科目组合类型Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdSubjectCombineMapper extends BaseMapper<StdSubjectCombine> {

  /**
   * 查询科目组合类型列表
   *
   * @param stdSubjectCombine 科目组合类型
   * @return 科目组合类型集合
   */
  List<StdSubjectCombine> selectStdSubjectCombineList(StdSubjectCombine stdSubjectCombine);

  /**
   * 加载科目组合信息
   *
   * @param projectId 分析项目Id
   */
  List<StdSubjectCombine> loadStdSubjectCombine(Long projectId);
}
