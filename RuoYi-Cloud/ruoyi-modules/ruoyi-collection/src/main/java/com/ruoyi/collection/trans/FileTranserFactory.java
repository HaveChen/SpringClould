package com.ruoyi.collection.trans;

import com.ruoyi.common.core.utils.file.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件转化器构建工厂。
 */
public class FileTranserFactory {

  /**
   * 创建文件转换器。
   *
   * @param file 本地文件
   * @return 返回文件转换器。
   */
  public static FileTranser create(File file) {
    try {
      return create(file.getName(), new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException("创建文件转换器时发生异常。", e);
    }
  }

  /**
   * 创建文件转换器。
   *
   * @param file 上传文件
   * @return 返回文件转换器。
   */
  public static FileTranser create(MultipartFile file) {
    try {
      return create(file.getOriginalFilename(), file.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException("创建文件转换器时发生异常。", e);
    }
  }

  /**
   * 创建文件转换器。
   *
   * @param fileName 文件名
   * @param in 文件流
   * @return 返回文件转换器实例。
   */
  public static FileTranser create(String fileName, InputStream in) {
    String ext = FileUtils.getFileType(fileName);
    if (".xls".equals(ext)) {
      return ExcelTranser.forXls(in);
    }
    if (".xlsx".equals(ext)) {
      return ExcelTranser.forXlsx(in);
    }
    if (".csv".equals(ext)) {
      return new CsvTranser(in);
    }
    throw new RuntimeException("不支持的文件类型[" + ext + "]");
  }

  /**
   * 私有构造方法。
   */
  private FileTranserFactory() {
  }
}
