package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltTestpaper;
import java.util.List;

/**
 * 试卷Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltTestpaperService extends IService<CltTestpaper> {


  /**
   * 查询试卷列表
   *
   * @param cltTestpaper 试卷
   * @return 试卷集合
   */
  public List<CltTestpaper> selectCltTestpaperList(CltTestpaper cltTestpaper);

  /**
   * 获取试卷（根据考试ID）
   *
   * @param examId 考试ID
   */
  List<CltTestpaper> getTestPaperByExamId(String examId);

  /**
   * 获取试卷（根据考试ID、科目代码）
   *
   * @param examId 考试ID
   * @param subjectCode 科目代码
   */
  CltTestpaper getTestPaperByExamIdAndSubjectCode(String examId, String subjectCode);

  /**
   * 根据考试删除试卷修改：试卷，试卷明细，细目表
   */
  boolean deleteTestpaperByexamId(String examId);

  /**
   * 从考试拷贝试卷到项目
   */
  void copyTestPaperToProject(Long examId, Long projectId);


  /**
   * 从考试拷贝参加考试的组织
   */
  void copyExamDeptToProject(Long examId, Long projectId);
}
