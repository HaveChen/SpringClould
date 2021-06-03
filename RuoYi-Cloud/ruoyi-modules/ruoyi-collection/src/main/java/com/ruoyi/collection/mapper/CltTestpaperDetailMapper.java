package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltTestpaperDetail;
import java.util.List;

/**
 * 试卷详情Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltTestpaperDetailMapper extends BaseMapper<CltTestpaperDetail> {

  /**
   * 查询试卷详情列表
   *
   * @param cltTestpaperDetail 试卷详情
   * @return 试卷详情集合
   */
  public List<CltTestpaperDetail> selectCltTestpaperDetailList(
      CltTestpaperDetail cltTestpaperDetail);

  int deleteTestpaperDetailByExamId(String examId);

 // void copyTestPaperDetalToProject(AlyTestpaper alyTestpaper);
}
