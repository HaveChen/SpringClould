package com.ruoyi.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.domain.StdSubjectCombine;
import java.util.List;

/**
 * 科目组合类型Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdSubjectCombineService extends IService<StdSubjectCombine> {


  /**
   * 查询科目组合类型列表
   *
   * @param stdSubjectCombine 科目组合类型
   * @return 科目组合类型集合
   */
  public List<StdSubjectCombine> selectStdSubjectCombineList(StdSubjectCombine stdSubjectCombine);

  /**
   * 根据项目加载存在的科目组合
   */
  List<StdSubjectCombine> loadStdSubjectCombine(Long projectId);
}
