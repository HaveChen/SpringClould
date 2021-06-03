package com.ruoyi.collection.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.collection.domain.CltProgress;
import feign.Param;
import java.util.List;

/**
 * 采集进度Mapper接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface CltProgressMapper extends BaseMapper<CltProgress> {

  /**
   * 查询采集进度列表
   *
   * @param cltProgress 采集进度
   * @return 采集进度集合
   */
  public List<CltProgress> selectCltProgressList(CltProgress cltProgress);

  /**
   * 根据文件导入状态
   */
  void updateImportStatus(@Param("progressId") Long progressId,
      @Param("importType") String importType);

}
