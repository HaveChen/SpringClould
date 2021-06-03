package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltExamMode;
import java.util.List;

/**
 * 考试模式Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltExamModeMapper extends BaseMapper<CltExamMode> {

  /**
   * 查询考试模式列表
   *
   * @param cltExamMode 考试模式
   * @return 考试模式集合
   */
  public List<CltExamMode> selectCltExamModeList(CltExamMode cltExamMode);

}
