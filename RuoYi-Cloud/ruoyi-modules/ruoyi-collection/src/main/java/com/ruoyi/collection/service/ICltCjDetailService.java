package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltCjDetail;
import java.util.List;

/**
 * 成绩数据采集Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltCjDetailService extends IService<CltCjDetail> {


  /**
   * 查询成绩数据采集列表
   *
   * @param cltCjDetail 成绩数据采集
   * @return 成绩数据采集集合
   */
  public List<CltCjDetail> selectCltCjDetailList(CltCjDetail cltCjDetail);

  /**
   * 根据考试删除成绩
   */
  boolean deleteCjByexamId(String examId);
}
