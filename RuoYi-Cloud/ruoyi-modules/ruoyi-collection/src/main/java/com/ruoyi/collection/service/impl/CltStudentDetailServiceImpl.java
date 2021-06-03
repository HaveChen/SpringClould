package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltStudentDetail;
import com.ruoyi.collection.mapper.CltStudentDetailMapper;
import com.ruoyi.collection.service.ICltStudentDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生信息采集Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltStudentDetailServiceImpl extends
    ServiceImpl<CltStudentDetailMapper, CltStudentDetail> implements ICltStudentDetailService {

  @Autowired
  private CltStudentDetailMapper cltStudentDetailMapper;


  /**
   * 查询学生信息采集列表
   *
   * @param cltStudentDetail 学生信息采集
   * @return 学生信息采集
   */
  @Override
  public List<CltStudentDetail> selectCltStudentDetailList(CltStudentDetail cltStudentDetail) {
    return cltStudentDetailMapper.selectCltStudentDetailList(cltStudentDetail);
  }

  @Override
  public boolean deleteStudentByexamId(String examId) {
    return cltStudentDetailMapper.deleteStudentByExamId(examId) >= 0;
  }


  @Override
  public String getImportCodes(String examId) {
    return cltStudentDetailMapper.getImportCodes(examId);
  }


}
