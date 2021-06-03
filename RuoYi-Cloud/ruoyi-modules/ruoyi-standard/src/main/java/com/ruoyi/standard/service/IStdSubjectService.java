package com.ruoyi.standard.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.standard.api.domain.StdSubject;
import java.util.List;

/**
 * 科目管理Service接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface IStdSubjectService extends IService<StdSubject> {


  /**
   * 查询科目管理列表
   *
   * @param stdSubject 科目管理
   * @return 科目管理集合
   */
  public List<StdSubject> selectStdSubjectList(StdSubject stdSubject);
  
  /**
   * 根据科目代码查询科目
   * @param subjectCode 科目代码
   * @return 科目对象
   */
  public StdSubject selectStdSubjectBySubjectCode(String subjectCode);

}
