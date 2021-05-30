package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdAdminClass;
import com.ruoyi.standard.mapper.StdAdminClassMapper;
import com.ruoyi.standard.service.IStdAdminClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行政班Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdAdminClassServiceImpl extends
    ServiceImpl<StdAdminClassMapper, StdAdminClass> implements IStdAdminClassService {

  @Autowired
  private StdAdminClassMapper stdAdminClassMapper;


  /**
   * 查询行政班列表
   *
   * @param stdAdminClass 行政班
   * @return 行政班
   */
  @Override
  public List<StdAdminClass> selectStdAdminClassList(StdAdminClass stdAdminClass) {
    return stdAdminClassMapper.selectStdAdminClassList(stdAdminClass);
  }

  @Override
  public boolean saveAdminClass(StdAdminClass stdAdminClass) {
    return save(stdAdminClass);
  }

  @Override
  public boolean updateAdminClassById(StdAdminClass stdAdminClass) {
    return updateById(stdAdminClass);
  }
}
