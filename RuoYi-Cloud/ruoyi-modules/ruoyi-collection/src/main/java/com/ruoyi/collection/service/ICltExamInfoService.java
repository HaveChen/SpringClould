package com.ruoyi.collection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExamInfo;
import java.util.List;

/**
 * 考试信息Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExamInfoService extends IService<CltExamInfo> {


  /**
   * 查询考试信息列表
   *
   * @param cltExamInfo 考试信息
   * @return 考试信息集合
   */
  public List<CltExamInfo> selectCltExamInfoList(CltExamInfo cltExamInfo);

  /**
   * 新增考试和初始化考试相关数据
   */
  boolean saveExamInfo(CltExamInfo cltExamInfo);

  /**
   * 删除考试和考试相关数据
   */
  boolean removeExamsByIds(List<Long> asList);

  /**
   * 根据届别代码获取考试
   *
   * @param graduateCode 届别代码
   */
  List<CltExamInfo> getExamByGraduateCode(String graduateCode);

  /**
   * 导入报名库之后初始化参加考试的组织信息
   */
  void initExamIncludeDeptCode(CltExamInfo examInfo);
}
