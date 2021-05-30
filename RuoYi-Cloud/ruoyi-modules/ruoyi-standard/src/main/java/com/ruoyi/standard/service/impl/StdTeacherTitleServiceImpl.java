package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdTeacherTitle;
import com.ruoyi.standard.mapper.StdTeacherTitleMapper;
import com.ruoyi.standard.service.IStdTeacherTitleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师职称Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdTeacherTitleServiceImpl extends
    ServiceImpl<StdTeacherTitleMapper, StdTeacherTitle> implements IStdTeacherTitleService {

  @Autowired
  private StdTeacherTitleMapper stdTeacherTitleMapper;


  /**
   * 查询教师职称列表
   *
   * @param stdTeacherTitle 教师职称
   * @return 教师职称
   */
  @Override
  public List<StdTeacherTitle> selectStdTeacherTitleList(StdTeacherTitle stdTeacherTitle) {
    return stdTeacherTitleMapper.selectStdTeacherTitleList(stdTeacherTitle);
  }


}
