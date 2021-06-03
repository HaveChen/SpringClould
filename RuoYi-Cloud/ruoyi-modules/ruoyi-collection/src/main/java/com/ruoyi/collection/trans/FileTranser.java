package com.ruoyi.collection.trans;

import com.ruoyi.collection.domain.CltExamDataField;
import java.util.List;

public interface FileTranser {

  /**
   * 将Excel数据转换成指定类型的Bean对象。
   *
   * @param beanClass Bean的Class类型
   * @param fieldNames 属性名列表
   * @return 返回Bean对象列表。
   */
  <T> List<T> trans(Class<T> beanClass, String... fieldNames);

  /**
   * 将Excel数据转换成指定类型的Bean对象。
   *
   * @param beanClass Bean的Class类型
   * @param etlDatafields 导入配置
   * @return 返回Bean对象列表。
   */
  <T> List<T> trans(Class<T> beanClass, List<CltExamDataField> etlDatafields);
}
