package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltTestpaper;
import com.ruoyi.collection.mapper.CltExamInfoMapper;
import com.ruoyi.collection.mapper.CltItemDetailMapper;
import com.ruoyi.collection.mapper.CltTestpaperDetailMapper;
import com.ruoyi.collection.mapper.CltTestpaperMapper;
import com.ruoyi.collection.service.ICltTestpaperService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltTestpaperServiceImpl extends
    ServiceImpl<CltTestpaperMapper, CltTestpaper> implements ICltTestpaperService {

  @Autowired
  private CltTestpaperMapper cltTestpaperMapper;
  @Autowired
  private CltTestpaperDetailMapper cltTestpaperDetailMapper;
  @Autowired
  private CltItemDetailMapper cltItemDetailMapper;
  @Autowired
  private CltExamInfoMapper cltExamInfoMapper;

  /**
   * 查询试卷列表
   *
   * @param cltTestpaper 试卷
   * @return 试卷
   */
  @Override

  public List<CltTestpaper> selectCltTestpaperList(CltTestpaper cltTestpaper) {
    return cltTestpaperMapper.selectCltTestpaperList(cltTestpaper);
  }

  @Override
  public List<CltTestpaper> getTestPaperByExamId(String examId) {
    QueryWrapper<CltTestpaper> q = new QueryWrapper<>();
    q.eq("exam_id", examId);
    return list(q);
  }

  @Override
  public CltTestpaper getTestPaperByExamIdAndSubjectCode(String examId, String subjectCode) {
    QueryWrapper<CltTestpaper> q = new QueryWrapper<>();
    q.eq("exam_id", examId);
    q.eq("subject_code", subjectCode);
    return getOne(q);
  }

  @Transactional
  @Override
  public boolean deleteTestpaperByexamId(String examId) {
    //删除细目表
    cltItemDetailMapper.deleteItemDetailByExamId(examId);
    //删除试卷
    cltTestpaperDetailMapper.deleteTestpaperDetailByExamId(examId);
    //删除试卷明细
    cltTestpaperMapper.deleteTestpaperByexamId(examId);
    return true;
  }

  @Override
  public void copyTestPaperToProject(Long examId, Long projectId) {
    //添加试卷
    cltTestpaperMapper.copyTestPaperToProject(examId, projectId);
  }


  @Override
  public void copyExamDeptToProject(Long examId, Long projectId) {
    cltExamInfoMapper.copyExamDeptToProject(examId, projectId);
  }
}
