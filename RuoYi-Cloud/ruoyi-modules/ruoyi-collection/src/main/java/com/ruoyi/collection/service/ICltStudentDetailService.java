package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltStudentDetail;
import java.util.List;

/**
 * 学生信息采集Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltStudentDetailService extends IService<CltStudentDetail> {


  /**
   * 查询学生信息采集列表
   *
   * @param cltStudentDetail 学生信息采集
   * @return 学生信息采集集合
   */
  List<CltStudentDetail> selectCltStudentDetailList(CltStudentDetail cltStudentDetail);

  /**
   * 根据考试删除学生
   */
  boolean deleteStudentByexamId(String examId);


  /**
   * 获取导入的学校代码串
   */
  String getImportCodes(String s);
}
