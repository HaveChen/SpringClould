package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltItemDetail;
import java.util.List;

/**
 * 细目信息采集Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltItemDetailMapper extends BaseMapper<CltItemDetail> {

  /**
   * 查询细目信息采集列表
   *
   * @param cltItemDetail 细目信息采集
   * @return 细目信息采集集合
   */
  public List<CltItemDetail> selectCltItemDetailList(CltItemDetail cltItemDetail);

  /**
   * 根据考试删除细目表
   *
   * @param examId 考试Id
   */
  int deleteItemDetailByExamId(String examId);

}
