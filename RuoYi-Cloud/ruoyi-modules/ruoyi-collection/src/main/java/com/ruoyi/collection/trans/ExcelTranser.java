package com.ruoyi.collection.trans;

import com.ruoyi.collection.domain.CltExamDataField;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel文件到Bean对象转换器。
 */
@Slf4j
public class ExcelTranser extends AbstractFileTranser {

  private Sheet sheet;

  /**
   * 读取xls文件创建转换器实例。
   *
   * @param in xls文件输入流
   * @return 返回转换器实例。
   */
  public static ExcelTranser forXls(InputStream in) {
    return new ExcelTranser(in, false);
  }

  /**
   * 读取xlsx文件创建转换器实例。
   *
   * @param in xlsx文件输入流
   * @return 返回转换器实例。
   */
  public static ExcelTranser forXlsx(InputStream in) {
    return new ExcelTranser(in, true);
  }

  /**
   * 私有构造方法。
   *
   * @param in 输入流
   * @param isXlsx 是否xlsx文件
   */
  private ExcelTranser(InputStream in, Boolean isXlsx) {
    try (Workbook workbook = isXlsx ? new XSSFWorkbook(in) : new HSSFWorkbook(in)) {
      sheet = workbook.getSheetAt(0);
    } catch (IOException e) {
      throw new RuntimeException("读取Excel文件时发生异常。", e);
    }
  }

  @Override
  public <T> List<T> trans(Class<T> beanClass, String... fieldNames) {
    List<T> beans = new ArrayList<>();
    List<String> colNames = genColNames(fieldNames);
    Iterator<Row> rows = sheet.rowIterator();
    while (rows.hasNext()) {
      Row row = rows.next();
      Map<String, String> rowMap = rowToMap(row, colNames);
      beans.add(mapToBean(rowMap, beanClass, fieldNames));
    }
    return beans;
  }

  @Override
  public <T> List<T> trans(Class<T> beanClass, List<CltExamDataField> etlDatafields) {
    List<T> beans = new ArrayList<>();
    Map<String, Integer> colNames = genColNames(etlDatafields);
    Iterator<Row> rows = sheet.rowIterator();
    Row row = null;
    long indexValue = 2l;
    while (rows.hasNext()) {
      try {
        row = rows.next();
        Map<String, String> rowMap = rowToMap(row, colNames);
        beans.add(mapToBean(rowMap, beanClass, etlDatafields));
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        indexValue++;
      }
    }
    return beans;
  }


  /**
   * 将Excel行记录转换成Map。
   *
   * @param row 行记录
   * @param colNames 列名列表
   * @return 返回转换的Map。
   */
  private Map<String, String> rowToMap(Row row, List<String> colNames) {
    Map<String, String> resultMap = new HashMap<>();
    row.forEach(
        cell -> {
          cell.setCellType(CellType.STRING);
          String name = colNames.get(cell.getColumnIndex());
          String value = cell.getStringCellValue();
          resultMap.put(name, value);
        });
    return resultMap;
  }

  /**
   * 将Excel行记录转换成Map。
   *
   * @param row 行记录
   * @param colNames 列名及对应文件列下标
   * @return 返回转换的Map。
   */
  private Map<String, String> rowToMap(Row row, Map<String, Integer> colNames) {
    Map<String, String> resultMap = new HashMap<>();
    for (String key : colNames.keySet()) {
      String value = row.getCell(colNames.get(key)) + "";
      resultMap.put(key, value);
    }
    return resultMap;
  }

  /**
   * 生成列名列表。
   *
   * @param fieldNames 属性名列表
   * @return 返回列名列表。
   */
  private List<String> genColNames(String... fieldNames) {
    List<String> colNames = new ArrayList<>();
    Row row = sheet.getRow(0);
    row.forEach(cell -> colNames.add(cell.getStringCellValue()));
    for (int i = 0; i < fieldNames.length; i++) {
      colNames.set(i, fieldNames[i]);
    }
    sheet.removeRow(row);
    return colNames;
  }

  /**
   * 生成列名列表。
   *
   * @param etlDatafields 导入默认字段配置
   * @return 返回列名列表及对应文件的列标。
   */
  private Map<String, Integer> genColNames(List<CltExamDataField> etlDatafields) {
    List<String> colNames = new ArrayList<>();
    Map<String, Integer> colMaps = new HashMap();
    String msg = "";
    // 获取表头数据
    Row row = sheet.getRow(0);
    // colNames表头所有的title值
    row.forEach(cell -> colNames.add(cell.getStringCellValue()));
    // 根据导入配置对应表头
    for (int i = 0; i < etlDatafields.size(); i++) {
      CltExamDataField etlFs = etlDatafields.get(i);
      // 对应对象的字段名称
      String fieldName = etlFs.getFieldName();
      // 默认对应表头名称，可以多个，有|隔开
      String defaultName = etlFs.getDefaultName();
      if (defaultName.isEmpty()) {
        msg += fieldName + "缺少默认字段设置;";
        continue;
      }
      String[] strs = defaultName.split("\\|");
      int index = 0;
      // 配对是否成功
      boolean isPair = false;
      for (String strE : colNames) {
        for (String strP : strs) {
          if (strE.equalsIgnoreCase(strP)) {
            colMaps.put(fieldName, index);
            isPair = true;
            continue;
          }
        }
        index++;
      }
      // 如果配对失败，查看是否必须字段
      if (!isPair && "0".equalsIgnoreCase(etlFs.getIsNeed())) {
        log.debug(fieldName + "没有匹配到列数据。");
        msg += "必须字段'" + fieldName + "'没有匹配到数据。";
      }
    }
    sheet.removeRow(row);
    return colMaps;
  }
}
