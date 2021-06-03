package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltTestpaper;
import feign.Param;
import java.util.List;

/**
 * 试卷Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltTestpaperMapper extends BaseMapper<CltTestpaper> {

  /**
   * 查询试卷列表
   *
   * @param cltTestpaper 试卷
   * @return 试卷集合
   */
  public List<CltTestpaper> selectCltTestpaperList(CltTestpaper cltTestpaper);

  int deleteTestpaperByexamId(String examId);

  void copyTestPaperToProject(@Param("examId") Long examId, @Param("projectId") Long projectId);
}
