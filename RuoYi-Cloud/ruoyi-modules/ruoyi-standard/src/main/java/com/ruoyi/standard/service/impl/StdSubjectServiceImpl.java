package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdSubject;
import com.ruoyi.standard.mapper.StdSubjectMapper;
import com.ruoyi.standard.service.IStdSubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 科目管理Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdSubjectServiceImpl extends
    ServiceImpl<StdSubjectMapper, StdSubject> implements IStdSubjectService {

  @Autowired
  private StdSubjectMapper stdSubjectMapper;


  /**
   * 查询科目管理列表
   *
   * @param stdSubject 科目管理
   * @return 科目管理
   */
  @Override
  public List<StdSubject> selectStdSubjectList(StdSubject stdSubject) {
    return stdSubjectMapper.selectStdSubjectList(stdSubject);
  }

  /**
   * 根据科目代码查询科目
   * @param subjectCode 科目代码
   * @return 科目对象
   */
  @Override
  public StdSubject selectStdSubjectBySubjectCode(String subjectCode) {
    StdSubject stdSubject = new StdSubject();
    stdSubject.setSubjectCode(subjectCode);
    List<StdSubject> list = selectStdSubjectList(stdSubject);
    return list.size() > 0 ? list.get(0) : null;
  }


}
