package com.ruoyi.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 *
 * @author ruoyi
 */
public interface ISysFileService {

  /**
   * 文件上传接口
   *
   * @param file 上传的文件
   * @return 访问地址
   */
  public String uploadFile(MultipartFile file) throws Exception;

  /**
   * 文件下载
   *
   * @param groupName 文件地址
   * @param fileName 下载后的文件名称
   */
  public String downloadFile(String groupName, String fileName);

  /**
   * 文件删除
   *
   * @param groupName 文件地址
   */
  public void deleteFile(String groupName);

}
