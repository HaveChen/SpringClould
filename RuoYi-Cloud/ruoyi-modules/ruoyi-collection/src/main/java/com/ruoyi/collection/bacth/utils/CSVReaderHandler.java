package com.ruoyi.collection.bacth.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import lombok.Getter;

@Getter
public class CSVReaderHandler {

  private Integer sumLine;
  private String titles;

  /**
   * 初始化csv文件，获取表头和总行数。
   *
   * @param file 文件。
   */
  public static CSVReaderHandler create(File file) {
    CSVReaderHandler csvReader = new CSVReaderHandler();
    try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), "GBK")) {
      LineNumberReader lnr = new LineNumberReader(in);
      csvReader.titles = lnr.readLine();
      lnr.skip(Long.MAX_VALUE);
      csvReader.sumLine = lnr.getLineNumber();
    } catch (Exception e) {
      throw new RuntimeException("打开文件失败。", e);
    }
    return csvReader;
  }

  /**
   * 私有构造方法。
   */
  private CSVReaderHandler() {
  }
}
