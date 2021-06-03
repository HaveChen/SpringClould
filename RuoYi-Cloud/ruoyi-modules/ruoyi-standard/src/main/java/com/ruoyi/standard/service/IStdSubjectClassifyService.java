package com.ruoyi.standard.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.api.domain.StdSubjectClassify;
import java.util.List;

/**
 * 科目分类Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdSubjectClassifyService extends IService<StdSubjectClassify> {


  /**
   * 查询科目分类列表
   *
   * @param stdSubjectClassify 科目分类
   * @return 科目分类集合
   */
  public List<StdSubjectClassify> selectStdSubjectClassifyList(StdSubjectClassify stdSubjectClassify);

}
