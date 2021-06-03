package com.ruoyi.collection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.collection.domain.CltExamDataField;
import java.util.List;

/**
 * 数据采集类型--考试Service接口
 *
 * @author chenyou
 * @date 2020-12-12
 */
public interface ICltExamDataFieldService extends IService<CltExamDataField> {


  /**
   * 查询数据采集类型--考试列表
   *
   * @param cltExamDataField 数据采集类型--考试
   * @return 数据采集类型--考试集合
   */
  List<CltExamDataField> selectCltExamDataFieldList(CltExamDataField cltExamDataField);

  /**
   * 查询采集配置
   *
   * @param examId 考试Id
   * @param importType 采集类型
   * @return 配置列表
   */
  List<CltExamDataField> selectCltExamDataFillds(String examId, String importType);
}
