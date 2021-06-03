package com.ruoyi.common.core.utils.file;

import com.ruoyi.common.core.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

  /**
   * 字符常量：斜杠 {@code '/'}
   */
  public static final char SLASH = '/';

  /**
   * 字符常量：反斜杠 {@code '\\'}
   */
  public static final char BACKSLASH = '\\';

  public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

  /**
   * 输出指定文件的byte数组
   *
   * @param filePath 文件路径
   * @param os 输出流
   */
  public static void writeBytes(String filePath, OutputStream os) throws IOException {
    FileInputStream fis = null;
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        throw new FileNotFoundException(filePath);
      }
      fis = new FileInputStream(file);
      byte[] b = new byte[1024];
      int length;
      while ((length = fis.read(b)) > 0) {
        os.write(b, 0, length);
      }
    } catch (IOException e) {
      throw e;
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  /**
   * 删除文件
   *
   * @param filePath 文件
   */
  public static boolean deleteFile(String filePath) {
    boolean flag = false;
    File file = new File(filePath);
    // 路径为文件且不为空则进行删除
    if (file.isFile() && file.exists()) {
      file.delete();
      flag = true;
    }
    return flag;
  }

  /**
   * 文件名称验证
   *
   * @param filename 文件名称
   * @return true 正常 false 非法
   */
  public static boolean isValidFilename(String filename) {
    return filename.matches(FILENAME_PATTERN);
  }

  /**
   * 从带有类型后缀的文件名中获取文件名的类型后缀。
   *
   * @param fileName 带有类型后缀的文件名
   * @return 返回文件名的类型后缀。
   */
  public static String getFileType(String fileName) {
    return fileName.substring(fileName.lastIndexOf('.'));
  }

  /**
   * 检查文件是否可下载
   *
   * @param resource 需要下载的文件
   * @return true 正常 false 非法
   */
  public static boolean checkAllowDownload(String resource) {
    // 禁止目录上跳级别
    if (StringUtils.contains(resource, "..")) {
      return false;
    }

    // 检查允许下载的文件规则
    if (ArrayUtils
        .contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource))) {
      return true;
    }

    // 不在允许下载的文件规则
    return false;
  }

  /**
   * 下载文件名重新编码
   *
   * @param request 请求对象
   * @param fileName 文件名
   * @return 编码后的文件名
   */
  public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
      throws UnsupportedEncodingException {
    final String agent = request.getHeader("USER-AGENT");
    String filename = fileName;
    if (agent.contains("MSIE")) {
      // IE浏览器
      filename = URLEncoder.encode(filename, "utf-8");
      filename = filename.replace("+", " ");
    } else if (agent.contains("Firefox")) {
      // 火狐浏览器
      filename = new String(fileName.getBytes(), "ISO8859-1");
    } else if (agent.contains("Chrome")) {
      // google浏览器
      filename = URLEncoder.encode(filename, "utf-8");
    } else {
      // 其它浏览器
      filename = URLEncoder.encode(filename, "utf-8");
    }
    return filename;
  }

  /**
   * 返回文件名
   *
   * @param filePath 文件
   * @return 文件名
   */
  public static String getName(String filePath) {
    if (null == filePath) {
      return null;
    }
    int len = filePath.length();
    if (0 == len) {
      return filePath;
    }
    if (isFileSeparator(filePath.charAt(len - 1))) {
      // 以分隔符结尾的去掉结尾分隔符
      len--;
    }

    int begin = 0;
    char c;
    for (int i = len - 1; i > -1; i--) {
      c = filePath.charAt(i);
      if (isFileSeparator(c)) {
        // 查找最后一个路径分隔符（/或者\）
        begin = i + 1;
        break;
      }
    }

    return filePath.substring(begin, len);
  }

  /**
   * 是否为Windows或者Linux（Unix）文件分隔符<br> Windows平台下分隔符为\，Linux（Unix）为/
   *
   * @param c 字符
   * @return 是否为Windows或者Linux（Unix）文件分隔符
   */
  public static boolean isFileSeparator(char c) {
    return SLASH == c || BACKSLASH == c;
  }

  /**
   * 下载文件名重新编码
   *
   * @param response 响应对象
   * @param realFileName 真实文件名
   */
  public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName)
      throws UnsupportedEncodingException {
    String percentEncodedFileName = percentEncode(realFileName);

    StringBuilder contentDispositionValue = new StringBuilder();
    contentDispositionValue.append("attachment; filename=")
        .append(percentEncodedFileName)
        .append(";")
        .append("filename*=")
        .append("utf-8''")
        .append(percentEncodedFileName);

    response.setHeader("Content-disposition", contentDispositionValue.toString());
  }

  /**
   * zip解压（默认解压在同级目录下）
   *
   * @param filePath zip源文件
   * @throws RuntimeException 解压失败会抛出运行时异常
   */
  public static List<File> unZip(String filePath) throws Exception {
    return unZip(filePath, new File(filePath).getParentFile().getPath() + File.separator);
  }

  /**
   * zip解压
   *
   * @param filePath zip源文件
   * @param descDir 解压后的目标文件夹
   * @throws RuntimeException 解压失败会抛出运行时异常
   */
  public static List<File> unZip(String filePath, String descDir)
      throws RuntimeException, IOException {
    List<File> files = new ArrayList<>();
    File zipFile = new File(filePath);
    File pathFile = new File(descDir);
    if (!pathFile.exists()) {
      pathFile.mkdirs();
    }
    ZipFile zip = new ZipFile(zipFile);
    for (Enumeration entries = zip.getEntries(); entries.hasMoreElements(); ) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      String zipEntryName = entry.getName();
      InputStream in = zip.getInputStream(entry);
      String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
      // 判断路径是否存在,不存在则创建文件路径
      File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
      if (!file.exists()) {
        file.mkdirs();
      }
      // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
      if (new File(outPath).isDirectory()) {
        continue;
      }
      // 输出文件路径信息
      // System.out.println(outPath);
      OutputStream out = new FileOutputStream(outPath);
      byte[] buf1 = new byte[1024];
      int len;
      while ((len = in.read(buf1)) > 0) {
        out.write(buf1, 0, len);
      }
      files.add(new File(outPath));
      in.close();
      out.close();
    }
    return files;
  }

  /**
   * 压缩文件
   *
   * @param file 目标文件
   * @param dest 压缩后的路径，包含压缩后的文件名称
   */
  public static void zip(MultipartFile file, String dest) throws Exception {
    File f = new File(file.getOriginalFilename());
    File zipFile = new File(dest);
    zipFile.createNewFile();
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
    // 设置压缩编码
    // out.setEncoding("GBK");//
    // 设置为GBK后在windows下就不会乱码了，如果要放到Linux或者Unix下就不要设置了
    zip(out, f, "");// 递归压缩方法
    out.close();
  }

  private static void zip(ZipOutputStream out, File f, String base) throws Exception {
    if (f.isDirectory()) { // 如果是文件夹，则获取下面的所有文件
      File[] fl = f.listFiles();
      out.putNextEntry(new ZipEntry(base + "/"));// 此处要将文件写到文件夹中只用在文件名前加"/"再加文件夹名
      base = base.length() == 0 ? "" : base + "/";
      for (int i = 0; i < fl.length; i++) {
        zip(out, fl[i], base + fl[i].getName());
      }
    } else { // 如果是文件，则压缩
      out.putNextEntry(new ZipEntry(base)); // 生成下一个压缩节点
      FileInputStream in = new FileInputStream(f); // 读取文件内容
      int len;
      byte[] buf = new byte[1024];
      while ((len = in.read(buf, 0, 1024)) != -1) {
        out.write(buf, 0, len); // 写入到压缩包
      }
      in.close();
    }
  }

  /**
   * 百分号编码工具方法
   *
   * @param s 需要百分号编码的字符串
   * @return 百分号编码后的字符串
   */
  public static String percentEncode(String s) throws UnsupportedEncodingException {
    String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
    return encode.replaceAll("\\+", "%20");
  }
}
