package com.ruoyi.standard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.standard.domain.StdTeachClass;
import com.ruoyi.standard.mapper.StdTeachClassMapper;
import com.ruoyi.standard.service.IStdTeachClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教学班Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Service
public class StdTeachClassServiceImpl extends
    ServiceImpl<StdTeachClassMapper, StdTeachClass> implements IStdTeachClassService {

  @Autowired
  private StdTeachClassMapper stdTeachClassMapper;


  /**
   * 查询教学班列表
   *
   * @param stdTeachClass 教学班
   * @return 教学班
   */
  @Override
  public List<StdTeachClass> selectStdTeachClassList(StdTeachClass stdTeachClass) {
    return stdTeachClassMapper.selectStdTeachClassList(stdTeachClass);
  }

  @Override
  public boolean saveTeachClass(StdTeachClass stdTeachClass) {
    return save(stdTeachClass);
  }

  @Override
  public boolean updateTeachClassById(StdTeachClass stdTeachClass) {
    return updateById(stdTeachClass);
  }


}
