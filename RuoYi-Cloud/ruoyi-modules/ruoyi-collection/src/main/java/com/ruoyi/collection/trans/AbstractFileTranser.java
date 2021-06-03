package com.ruoyi.collection.trans;

import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractFileTranser implements FileTranser {

  /**
   * 将Map转换成指定的Bean对象。
   *
   * @param rowMap Map
   * @param beanClass Bean类型
   * @param fieldNames 属性名列表
   * @return 返回转换后的Bean对象。
   */
  protected <T> T mapToBean(Map<String, String> rowMap, Class<T> beanClass, String... fieldNames) {
    try {
      T bean = beanClass.newInstance();
      for (String fieldName : fieldNames) {
        Field field = BeanUtils.findField(beanClass, fieldName);
        String strValue = rowMap.get(fieldName);
        Object fieldValue = transForm(strValue, field.getType());
        BeanUtils.setField(bean, field, fieldValue);
        rowMap.remove(fieldName);
      }
      if (!rowMap.isEmpty()) {
        BeanUtils.setField(bean, "extensions", rowMap.toString());
      }
      return bean;
    } catch (Exception e) {
      throw new RuntimeException("将Map转换成Bean时发生异常。", e);
    }
  }

  /**
   * 将Map转换成指定的Bean对象。
   *
   * @param rowMap Map
   * @param beanClass Bean类型
   * @param etlDatafields 字段配置
   * @return 返回转换后的Bean对象。
   */
  protected <T> T mapToBean(
      Map<String, String> rowMap, Class<T> beanClass, List<CltExamDataField> etlDatafields) {
    try {
      T bean = beanClass.newInstance();
      for (CltExamDataField etlF : etlDatafields) {
        if ("1".equalsIgnoreCase(etlF.getIsExtend())) {
          continue;
        }
        String fieldName = etlF.getFieldName();
        Field field = BeanUtils.findField(beanClass, fieldName);
        String strValue = rowMap.get(fieldName);
        Object fieldValue = transForm(strValue, field.getType());
        BeanUtils.setField(bean, field, fieldValue);
        rowMap.remove(fieldName);
      }
      if (!rowMap.isEmpty()) {
        BeanUtils.setField(bean, "extensions", rowMap.toString());
      }
      return bean;
    } catch (Exception e) {
      throw new RuntimeException("将Map转换成Bean时发生异常。", e);
    }
  }

  /**
   * 将字符串转换成指定类型的值。
   *
   * @param strValue 字符串值
   * @param valueType 待转换类型
   * @return 返回转换后的值对象。
   */
  private Object transForm(String strValue, Class<?> valueType) {
    if (strValue.isEmpty() && valueType != String.class) {
      throw new RuntimeException("空字符串不能转换成" + valueType.getName() + "类型的值。");
    }
    if (valueType == String.class) {
      return strValue;
    } else if (valueType == Double.class) {
      return Double.parseDouble(strValue);
    } else if (valueType == Float.class) {
      return Float.parseFloat(strValue);
    } else if (valueType == Integer.class) {
      return Integer.parseInt(strValue);
    } else if (valueType == Long.class) {
      return Long.parseLong(strValue);
    } else if (valueType == Boolean.class) {
      return Boolean.parseBoolean(strValue);
    } else if (valueType == Date.class) {
      return DateUtils.parseDate(strValue);
    }
    return "";
  }
}
