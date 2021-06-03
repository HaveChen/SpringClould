package com.ruoyi.collection.trans;

import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.common.core.constant.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Csv文件到Bean对象转换器。
 */
public class CsvTranser extends AbstractFileTranser {

  private BufferedReader reader;

  /**
   * 构造方法。
   *
   * @param in 输入流
   */
  public CsvTranser(InputStream in) {
    try {
      reader = new BufferedReader(new InputStreamReader(in, Constants.GBK));
    } catch (IOException e) {
      throw new RuntimeException("读取Csv文件时发生异常。", e);
    }
  }

  @Override
  public <T> List<T> trans(Class<T> beanClass, String... fieldNames) {
    try {
      List<T> beans = new ArrayList<>();
      List<String> colNames = genColNames(fieldNames);
      String lineData = null;
      while ((lineData = reader.readLine()) != null) {
        String[] item = lineData.split(",");
        Map<String, String> rowMap = rowToMap(item, colNames);
        beans.add(mapToBean(rowMap, beanClass, fieldNames));
      }
      reader.close();
      return beans;
    } catch (IOException e) {
      throw new RuntimeException("读取Csv文件时发生异常。", e);
    }
  }

  @Override
  public <T> List<T> trans(Class<T> beanClass, List<CltExamDataField> etlDatafields) {
    try {
      List<T> beans = new ArrayList<>();
      //      List<String> colNames = genColNames(fieldNames);
      //      String lineData = null;
      //      while ((lineData = reader.readLine()) != null) {
      //        String[] item = lineData.split(",");
      //        Map<String, String> rowMap = rowToMap(item, colNames);
      //        beans.add(mapToBean(rowMap, beanClass, fieldNames));
      //      }
      //      reader.close();
      return beans;
    } catch (Exception e) {
      throw new RuntimeException("读取Csv文件时发生异常。", e);
    }
  }


  /**
   * 将Csv行记录转换成Map。
   *
   * @param row 行记录
   * @return 返回转换的Map。
   */
  private Map<String, String> rowToMap(String[] row, List<String> colNames) {
    Map<String, String> resultMap = new HashMap<>();
    int colIndex = 0;
    for (String value : row) {
      String name = colNames.get(colIndex++);
      resultMap.put(name, value);
    }
    return resultMap;
  }

  /**
   * 生成列名列表。
   *
   * @param fieldNames 属性名列表
   * @return 返回列名列表。
   */
  private List<String> genColNames(String... fieldNames) throws IOException {
    List<String> colNames = new ArrayList<>();
    String[] header = reader.readLine().split(",");
    for (String head : header) {
      colNames.add(head);
    }
    for (int i = 0; i < fieldNames.length; i++) {
      colNames.set(i, fieldNames[i]);
    }
    return colNames;
  }
}
