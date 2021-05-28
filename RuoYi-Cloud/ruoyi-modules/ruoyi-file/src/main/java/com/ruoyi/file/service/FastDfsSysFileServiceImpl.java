package com.ruoyi.file.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS 文件存储
 *
 * @author ruoyi
 */
@Primary
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService {

  /**
   * 域名或本机访问地址
   */
  @Value("${fdfs.domain}")
  public String domain;

  @Autowired
  private FastFileStorageClient storageClient;

  /**
   * FastDfs文件上传接口
   *
   * @param file 上传的文件
   * @return 访问地址
   */
  @Override
  public String uploadFile(MultipartFile file) throws Exception {
    StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
        "png", null);
    downloadFile("group1",
        "M00/00/00/CiMBv2CnHgmAAkSRAADOmo1tUuc008.png");
    return domain + "/" + storePath.getFullPath();
  }

  @Override
  public String downloadFile(String groupName, String fileName) {
    // 文件访问地址
    String fullPath = "group1/M00/00/00/CiMBv2CnHgmAAkSRAADOmo1tUuc008.png";
    // 分离文件分组
    String group = fullPath.substring(0, fullPath.indexOf("/"));
    // 分离文件路径
    String path = fullPath.substring(fullPath.indexOf("/") + 1);
    // 进行文件下载
    byte[] buffer = storageClient.downloadFile(group, path, new DownloadByteArray());
    System.out.println(1111);
    return null;
  }

  @Override
  public void deleteFile(String groupName) {

  }
}
