package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExamMode;
import com.ruoyi.collection.mapper.CltExamModeMapper;
import com.ruoyi.collection.service.ICltExamModeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 考试模式Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExamModeServiceImpl extends
    ServiceImpl<CltExamModeMapper, CltExamMode> implements ICltExamModeService {

  @Autowired
  private CltExamModeMapper cltExamModeMapper;


  /**
   * 查询考试模式列表
   *
   * @param cltExamMode 考试模式
   * @return 考试模式
   */
  @Override
  public List<CltExamMode> selectCltExamModeList(CltExamMode cltExamMode) {
    return cltExamModeMapper.selectCltExamModeList(cltExamMode);
  }


}
