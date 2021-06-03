package com.ruoyi.collection.bacth.reader;

import com.ruoyi.collection.bacth.utils.ExcelReaderUtils;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltStudentDetail;
import java.util.List;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 学生文件读取。
 */
public class CltStudentReader extends FlatFileItemReader<CltStudentDetail> {

  private List<CltExamDataField> etlDatafields;

  private CltProgress cltProgress;

  private CltStudentReader() {
  }

  public CltStudentReader(List<CltExamDataField> etlDatafields, CltProgress cltProgress) {
    this.etlDatafields = etlDatafields;
    this.cltProgress = cltProgress;
  }

  /**
   * 初始化reader信息
   */
  public ExcelItemReader initItemReader() {
    String filePathAll = cltProgress.getFilePath();
    Resource resource = new FileSystemResource(filePathAll);
    ExcelItemReader reader = new ExcelItemReader(resource, etlDatafields, cltProgress);
    // 设置表头标识
    reader.setHeadLineRow(ExcelReaderUtils.headerLine);
    return reader;
  }
}
