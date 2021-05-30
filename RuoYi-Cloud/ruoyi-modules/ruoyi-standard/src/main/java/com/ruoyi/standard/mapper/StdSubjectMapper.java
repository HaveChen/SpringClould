package com.ruoyi.standard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.standard.domain.StdSubject;
import java.util.List;

/**
 * 科目管理Mapper接口
 *
 * @author chenyou
 * @date 2020-12-18
 */
public interface StdSubjectMapper extends BaseMapper<StdSubject> {

  /**
   * 查询科目管理列表
   *
   * @param stdSubject 科目管理
   * @return 科目管理集合
   */
  public List<StdSubject> selectStdSubjectList(StdSubject stdSubject);

}
