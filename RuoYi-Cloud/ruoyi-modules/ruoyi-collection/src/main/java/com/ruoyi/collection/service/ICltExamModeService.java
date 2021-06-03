package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExamMode;
import java.util.List;

/**
 * 考试模式Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExamModeService extends IService<CltExamMode> {


  /**
   * 查询考试模式列表
   *
   * @param cltExamMode 考试模式
   * @return 考试模式集合
   */
  public List<CltExamMode> selectCltExamModeList(CltExamMode cltExamMode);

}
