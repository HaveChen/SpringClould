package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdSubjectClassify;
import com.ruoyi.standard.mapper.StdSubjectClassifyMapper;
import com.ruoyi.standard.service.IStdSubjectClassifyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 科目分类Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdSubjectClassifyServiceImpl extends
    ServiceImpl<StdSubjectClassifyMapper, StdSubjectClassify> implements IStdSubjectClassifyService {

  @Autowired
  private StdSubjectClassifyMapper stdSubjectClassifyMapper;


  /**
   * 查询科目分类列表
   *
   * @param stdSubjectClassify 科目分类
   * @return 科目分类
   */
  @Override
  public List<StdSubjectClassify> selectStdSubjectClassifyList(StdSubjectClassify stdSubjectClassify) {
    return stdSubjectClassifyMapper.selectStdSubjectClassifyList(stdSubjectClassify);
  }


}
