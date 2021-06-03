package com.ruoyi.collection.bacth.utils;

import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.standard.api.domain.StdSubject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 *chenyou
 *2020/12/25  8:43
 */
@Slf4j
public class ExcelReaderUtils {

  /**
   * 设置表头行
   */
  public static final int headerLine = 0;

  /**
   * 获取Excel的Workbook
   *
   * @param inputStream 文件流
   * @param fileName 文件名称
   * @return Workbook
   */
  public static Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {
    Workbook workbook = null;
    // 判断Excel文件类型
    if (fileName.toUpperCase().indexOf(".XLSX") != -1) {
      workbook = new XSSFWorkbook(inputStream);
    } else {
      workbook = new HSSFWorkbook(inputStream);
    }
    return workbook;
  }

  /**
   * 根据sheetName从workBook 获取sheet
   *
   * @param workbook excle文件
   * @param sheetName sheet 名称
   * @return sheet页
   */
  public static Sheet getSheet(Workbook workbook, String sheetName) {
    // 如果没有指定sheet的名称，则默认取第一个
    if (StringUtils.isEmpty(sheetName)) {
      return workbook.getSheetAt(0);
    } else {
      return workbook.getSheet(sheetName);
    }
  }

  /**
   * 关闭打开Excel资源
   *
   * @param i InputStream
   * @param workbook workbook
   */
  public static void close(InputStream i, Workbook workbook) {
    try {
      if (i != null) {
        i.close();
      }
      if (workbook != null) {
        workbook.close();
      }
    } catch (Exception e) {
      log.error("Excel文件资源关闭失败。");
    }

  }

  /**
   * @param header 表头数据
   * @param fields 匹配规则
   * @param columnMap 返回的字段匹配情况
   * @param headerMap 返回的表头匹配情况
   */
  public static String checkTitle(Row header, List<CltExamDataField> fields,
      Map<String, Integer> columnMap, Map<String, Integer> headerMap) {
    String message = "";
    if (header != null) {
      message += initColunMap(header, fields, columnMap);
      initHeaderMap(header, headerMap);
    } else {
      message = "表头数据为空。";
    }

    return message;
  }

  /**
   * 初始化列头
   */
  private static void initHeaderMap(Row header, Map<String, Integer> headerMap) {
    for (int i = header.getFirstCellNum(); i < header.getLastCellNum(); i++) {
      Cell cell = header.getCell(i);
      // 表头的名称
      String title = "";
      switch (cell.getCellType()) {
        case NUMERIC:
          if (HSSFDateUtil.isCellDateFormatted(cell)) {
          } else {
            title = cell.getNumericCellValue() + "";
            // 文件里面设置了数值类型时，需要把用.0结尾的去掉
            if (title.endsWith(".0")) {
              title = title.replaceAll("\\.0", "");
            }
          }
          break;
        case BLANK:
          title = "";
          break;
        default:
          title = cell.getStringCellValue();
      }
      if (!headerMap.containsKey(title)) {
        headerMap.put(title, i);
      }
    }
  }

  /**
   * 初始化能匹配的列头
   */
  private static String initColunMap(Row header, List<CltExamDataField> fields,
      Map<String, Integer> columnMap) {
    String message = "";
    for (CltExamDataField etl : fields) {
      // 默认对应名称
      String defaultName = etl.getDefaultName();
      //是否必须
      String isNeed = etl.getIsNeed();
      //对应实体字段
      String fieldName = etl.getFieldName();
      defaultName += "|" + fieldName;
      log.debug("开始匹配字段:{}", fieldName);
      String[] strs = defaultName.split("\\|");
      boolean hasMaping = false;
      for (int i = header.getFirstCellNum(); i < header.getLastCellNum(); i++) {
        Cell cell = header.getCell(i);
        // 表头的名称
        String title = cell.getStringCellValue();
        //多个匹配时：匹配规则靠前，列靠前的优先
        for (String str : strs) {
          if (title.equalsIgnoreCase(str)) {
            hasMaping = true;
            columnMap.put(fieldName, i);
            log.debug("{} ：Field matching successful. Index is {}", fieldName, i + 1);
            break;
          }
        }
        if (hasMaping) {
          break;
        }
      }
      // 没有找到核对的值,而且是必须值
      if (!hasMaping && "0".equalsIgnoreCase(isNeed)) {
        message += "必须导入字段：'" + fieldName + "'匹配表头失败；";
        log.debug(
            "'{}' Field matching failed。Please check the field configuration '{}' or import file",
            fieldName,
            defaultName);
      }
    }
    return message;
  }

  /**
   * 根据文件名称返回文件导入数据类型
   *
   * @param fileName 文件名称
   * @return 返回导入类型：bmk，cj，xmb等等,如果返回"",表示为识别成功，需要进一步识别（文件内容识别）
   */
  public static String analysisFileTypeByName(String fileName) {
    String importType = "";
    if (fileName.indexOf("bmk") > -1 || fileName.indexOf("报名") > -1
        || fileName.indexOf("报名库") > -1) {
      importType = "bmk";
    } else if (fileName.indexOf("xmk") > -1 || fileName.indexOf("细目表") > -1) {
      importType = "xmb";
    } else if (fileName.indexOf("cj") > -1 || fileName.indexOf("成绩") > -1) {
      importType = "cj";
    } else if (fileName.indexOf("课程") > -1 || fileName.indexOf("course") > -1) {
      importType = "course";
    }
    return importType;
  }

  /**
   * 根据文件类型和文件名称，识别对应科目
   */
  public static String analysisFileMapSubjectCode(List<StdSubject> subjects, String fileName) {
    for (StdSubject sb : subjects) {
      String sbCode = sb.getSubjectCode();
      String sbName = sb.getSubjectName();
      if (fileName.indexOf(sbName) >= 0 || fileName.indexOf(sbCode) >= 0) {
        return sbCode;
      }
      String sbOtherName = sb.getSubjectOtherName();
      if (StringUtils.isNotEmpty(sbOtherName)) {
        String[] sbStrs = sbOtherName.split("|");
        for (String sbStr : sbStrs) {
          if (fileName.indexOf(sbStr) >= 0) {
            return sbCode;
          }
        }
      }
    }

    return "";
  }

  public static void main(String[] args) {
    System.out.println(analysisFileTypeByName("Cj123成绩"));
  }


}
