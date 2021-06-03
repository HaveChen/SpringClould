package com.ruoyi.collection.bacth.utils;

import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.common.core.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.batch.item.ItemStreamException;

/*
 *chenyou
 *2020/8/11  10:50
 *
 */
@Data
@Slf4j
public class ExcelReaderHandler {

  private Map<String, Integer> columnMap = new HashMap<>();
  private Map<String, Integer> headerMap = new HashMap<>();

  /**
   * 上传文件初始化文件内部信息
   *
   * @param cltProgress 文件信息
   * @param etls 解析文件配置
   */
  public CltProgress initFileMessage(CltProgress cltProgress, List<CltExamDataField> etls)
      throws Exception {
    String filePath = cltProgress.getFilePath();
    String fileName = cltProgress.getFileName();
    InputStream inputStream = null;
    Workbook workbook = null;
    Sheet sheet = null;
    try {
      inputStream = new FileInputStream(new File(filePath));
      workbook = ExcelReaderUtils.getWorkbook(inputStream, fileName);
      sheet = ExcelReaderUtils.getSheet(workbook, null);
      if (sheet == null) {
        throw new ItemStreamException("Can't get the specified sheet!");
      }
      // 获取表头数据
      Row header = sheet.getRow(ExcelReaderUtils.headerLine);
      String message = ExcelReaderUtils.checkTitle(header, etls, columnMap, headerMap);
      // 最后一行的序号
      int lastRowNum = sheet.getLastRowNum();
      // 数据总行数
      int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
      int totalRows = physicalNumberOfRows < lastRowNum ? physicalNumberOfRows : lastRowNum;
      cltProgress.setSumLine(totalRows);
      cltProgress.setFieldMapping(columnMap.toString());
      cltProgress.setAllField(headerMap.toString());
      cltProgress.setImportStatus("1");
      //记录匹配信息
      if (StringUtils.isNotEmpty(message)) {
        cltProgress.setRemark(message);
        cltProgress.setImportStatus("0");
      }
    } catch (Exception e) {
      cltProgress.setImportStatus("0");
      cltProgress.setRemark(e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("获取文件信息失败。");
    } finally {
      ExcelReaderUtils.close(inputStream, workbook);
    }
    return cltProgress;
  }
}
