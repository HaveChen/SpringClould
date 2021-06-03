package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltExamInfo;
import feign.Param;
import java.util.List;

/**
 * 考试信息Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltExamInfoMapper extends BaseMapper<CltExamInfo> {

  /**
   * 查询考试信息列表
   *
   * @param cltExamInfo 考试信息
   * @return 考试信息集合
   */
  public List<CltExamInfo> selectCltExamInfoList(CltExamInfo cltExamInfo);


  void insertDataFieldToExam(CltExamInfo cltExamInfo);

  void copyExamDeptToProject(@Param("examId") Long examId, @Param("projectId") Long projectId);

  void cleanExamData(Long examId);

  void updateDeptAllCodeById(@Param("examId") Long examId,
      @Param("deptAllCode") String deptAllCode);
}
