package com.ruoyi.collection.bacth.reader;

import com.ruoyi.collection.bacth.utils.ExcelReaderUtils;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltProgressDetail;
import com.ruoyi.collection.service.ICltProgressDetailService;
import com.ruoyi.common.core.exception.BaseException;
import com.ruoyi.common.core.utils.MapDataUtils;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

/*
 *Excel文件公共Reader类
 *chenyou
 *2020/8/5  10:17
 */
@Slf4j
public class ExcelItemReader extends FlatFileItemReader {

  private Resource resource;
  private String sheetName = "";
  private Integer headLineRow = 0;
  private CltProgress cltProgress;
  // String:fields名称，integer：在文件中的列标
  private Map<String, Integer> columnMap = new HashMap<>();
  private Map<String, Integer> allColumnMap = new HashMap<>();
  private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
  List<CltExamDataField> fields;
  private Sheet sheet;
  // 当前行数据
  private Integer lineCount;
  private Integer totalRows;
  private List<CltProgressDetail> details = new ArrayList<>();
  @Autowired
  private ICltProgressDetailService detailService;

  /**
   * @param resource 资源文件
   * @param fields 字段对应信息
   */
  public ExcelItemReader(Resource resource, List<CltExamDataField> fields,
      CltProgress cltProgress) {
    this.cltProgress = cltProgress;
    this.resource = resource;
    this.fields = fields;
    super.setLineMapper(
        new LineMapper() {
          @Override
          public Object mapLine(String line, int lineNumber) throws Exception {
            return line;
          }
        });
  }


  private ExcelItemReader() {
  }

  /**
   * 打开文件
   */
  @Override
  public void open(ExecutionContext executioncontext) throws ItemStreamException {
    String fileName = resource.getFilename();
    log.info("open Excel...{}.", fileName);
    Workbook workbook = null;
    InputStream inputStream = null;
    try {
      inputStream = resource.getInputStream();
      workbook = ExcelReaderUtils.getWorkbook(inputStream, fileName);
      sheet = ExcelReaderUtils.getSheet(workbook, sheetName);
      if (sheet == null) {
        throw new ItemStreamException("Can't get the specified sheet!");
      }
      // 获取表头map数据
      initMappingFeild();
      lineCount = headLineRow + 1;
      // 最后一行的序号
      int lastRowNum = sheet.getLastRowNum();
      // 数据总行数
      int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
      totalRows = physicalNumberOfRows < lastRowNum ? physicalNumberOfRows : lastRowNum;
      log.debug("total rows:" + totalRows);
    } catch (Exception e) {
      log.error("open excel:{}  fail!", fileName, e);
      throw new ItemStreamException("打开'" + fileName + "'文件失败。" + e);
    } finally {
      ExcelReaderUtils.close(inputStream, workbook);
    }
  }

  private void initMappingFeild() {
    String fields = cltProgress.getFieldMapping();
    columnMap = MapDataUtils.stringToMap(fields);
    String allFields = cltProgress.getAllField();
    allColumnMap = MapDataUtils.stringToMap(allFields);
  }

  /**
   * 文件数据读取
   */
  @Override
  public Object read() throws Exception {
    if (lineCount > totalRows) {
      log.info("read over all rows!");
      if (details.size() > 0) {
        saveErrorData();
      }
      return null;
    }
    Map<String, String> dataMap = new HashMap<>();
    try {
      Row dataRow = sheet.getRow(lineCount);
      // 读取配置字段
      for (String key : columnMap.keySet()) {
        int index = columnMap.get(key);
        String dataValue = readValue(dataRow, index);
        dataMap.put(key, dataValue);
      }
      // 读取扩展字段
      for (String key : allColumnMap.keySet()) {
        int index = allColumnMap.get(key);
        if (columnMap.containsValue(index)) {
          continue;
        }
        String dataValue = readValue(dataRow, index);
        dataMap.put(key, dataValue);
      }
    } catch (Exception e) {
      try {
        buildErrorData(lineCount, e);
      } catch (Exception ex) {
        log.error("报错异常数据错误", ex);
      }
      log.error("第{}行数据解析异常。", lineCount);
      dataMap.clear();
    } finally {
      lineCount++;
    }
    return dataMap;
  }

  private String readValue(Row dataRow, int index) {
    Cell cell = dataRow.getCell(index);
    if (cell == null || index == 3) {
      throw new BaseException("数据导入", "第" + (index + 1) + "列数据存在问题");
    }
    String dataValue = "";
    try {
      switch (cell.getCellType()) {
        case NUMERIC:
          if (HSSFDateUtil.isCellDateFormatted(cell)) {
            Date date = cell.getDateCellValue();
            dataValue = dateFormat.format(date);
          } else {
            dataValue = cell.getNumericCellValue() + "";
            // 文件里面设置了数值类型时，需要把用.0结尾的去掉
            if (dataValue.endsWith(".0")) {
              dataValue = dataValue.replaceAll("\\.0", "");
            }
          }
          break;
        case BLANK:
          dataValue = "";
          break;
        default:
          dataValue = cell.getStringCellValue();
      }
    } catch (Exception e) {
      throw new BaseException("数据导入", "第" + (index + 1) + "列数据存在问题");
    }
    return dataValue;
  }

  /**
   * 错误数据记录
   */
  private void buildErrorData(Integer rowIndex, Exception e) {
    CltProgressDetail detail = new CltProgressDetail();
    detail.setProgressId(cltProgress.getProgressId());
    detail.setErrorMessage(e.getMessage());
    detail.setLine(rowIndex);
    detail.setErrorTypeCode("reader");
    details.add(detail);
    if (details.size() >= 2000) {
      saveErrorData();
    }
  }

  /**
   * 保存异常日志
   */
  private void saveErrorData() {
    try {
      detailService.saveBatch(details);
      details.clear();
    } catch (Exception e) {
      log.error("报错异常日志失败");
    }
  }

  /**
   * 设置表头行标。默认为0（第一行数据）
   */
  public void setHeadLineRow(Integer headLineRow) {
    this.headLineRow = headLineRow;
  }

  /**
   * 设置文件的sheet的名称
   */
  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }
}
