package com.ruoyi.collection.bacth.reader;

import com.ruoyi.collection.bacth.utils.ExcelReaderUtils;
import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import java.util.List;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 课程文件读取。
 */
public class CltCourseReader extends FlatFileItemReader<CltCourse> {

  private List<CltExamDataField> etlDatafields;

  private CltProgress cltProgress;

  private CltCourseReader() {
  }

  public CltCourseReader(List<CltExamDataField> etlDatafields, CltProgress cltProgress) {
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
