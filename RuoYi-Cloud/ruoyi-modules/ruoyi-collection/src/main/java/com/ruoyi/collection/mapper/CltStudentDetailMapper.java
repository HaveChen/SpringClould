package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltStudentDetail;
import java.util.List;

/**
 * 学生信息采集Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltStudentDetailMapper extends BaseMapper<CltStudentDetail> {

  /**
   * 查询学生信息采集列表
   *
   * @param cltStudentDetail 学生信息采集
   * @return 学生信息采集集合
   */
  List<CltStudentDetail> selectCltStudentDetailList(CltStudentDetail cltStudentDetail);

  /**
   * 删除考试学生信息
   */
  int deleteStudentByExamId(String examId);


  String getImportCodes(String examId);
}
