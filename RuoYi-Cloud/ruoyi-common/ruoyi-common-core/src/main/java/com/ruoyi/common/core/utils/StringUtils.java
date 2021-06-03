package com.ruoyi.common.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.text.StrFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

/**
 * 字符串工具类
 *
 * @author ruoyi
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {

  /**
   * 空字符串
   */
  private static final String NULLSTR = "";

  /**
   * 下划线
   */
  private static final char SEPARATOR = '_';

  /**
   * 获取参数不为空值
   *
   * @param value defaultValue 要判断的value
   * @return value 返回值
   */
  public static <T> T nvl(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }

  /**
   * * 判断一个Collection是否为空， 包含List，Set，Queue
   *
   * @param coll 要判断的Collection
   * @return true：为空 false：非空
   */
  public static boolean isEmpty(Collection<?> coll) {
    return isNull(coll) || coll.isEmpty();
  }

  /**
   * * 判断一个Collection是否非空，包含List，Set，Queue
   *
   * @param coll 要判断的Collection
   * @return true：非空 false：空
   */
  public static boolean isNotEmpty(Collection<?> coll) {
    return !isEmpty(coll);
  }

  /**
   * * 判断一个对象数组是否为空
   *
   * @param objects 要判断的对象数组 * @return true：为空 false：非空
   */
  public static boolean isEmpty(Object[] objects) {
    return isNull(objects) || (objects.length == 0);
  }

  /**
   * * 判断一个对象数组是否非空
   *
   * @param objects 要判断的对象数组
   * @return true：非空 false：空
   */
  public static boolean isNotEmpty(Object[] objects) {
    return !isEmpty(objects);
  }

  /**
   * * 判断一个Map是否为空
   *
   * @param map 要判断的Map
   * @return true：为空 false：非空
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return isNull(map) || map.isEmpty();
  }

  /**
   * * 判断一个Map是否为空
   *
   * @param map 要判断的Map
   * @return true：非空 false：空
   */
  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }

  /**
   * 是否是压缩文件
   *
   * @param fileType 文件类型
   * <br> 目前支持的压缩文件包括：zip，rar
   */
  public static boolean isCompressFile(String fileType) {
    return "zip".equalsIgnoreCase(fileType) || "rar".equalsIgnoreCase(fileType);
  }

  /**
   * * 判断一个字符串是否为空串
   *
   * @param str String
   * @return true：为空 false：非空
   */
  public static boolean isEmpty(String str) {
    return isNull(str) || NULLSTR.equals(str.trim());
  }

  /**
   * * 判断一个字符串是否为非空串
   *
   * @param str String
   * @return true：非空串 false：空串
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  /**
   * * 判断一个对象是否为空
   *
   * @param object Object
   * @return true：为空 false：非空
   */
  public static boolean isNull(Object object) {
    return object == null;
  }

  /**
   * * 判断一个对象是否非空
   *
   * @param object Object
   * @return true：非空 false：空
   */
  public static boolean isNotNull(Object object) {
    return !isNull(object);
  }

  /**
   * * 判断一个对象是否是数组类型（Java基本型别的数组）
   *
   * @param object 对象
   * @return true：是数组 false：不是数组
   */
  public static boolean isArray(Object object) {
    return isNotNull(object) && object.getClass().isArray();
  }

  /**
   * 去空格
   */
  public static String trim(String str) {
    return (str == null ? "" : str.trim());
  }

  /**
   * 截取字符串
   *
   * @param str 字符串
   * @param start 开始
   * @return 结果
   */
  public static String substring(final String str, int start) {
    if (str == null) {
      return NULLSTR;
    }

    if (start < 0) {
      start = str.length() + start;
    }

    if (start < 0) {
      start = 0;
    }
    if (start > str.length()) {
      return NULLSTR;
    }

    return str.substring(start);
  }

  /**
   * 将字符串转换成指定类型的值。
   *
   * @param strValue 字符串值
   * @param valueType 待转换类型
   * @return 返回转换后的值对象。
   */
  public static Object transForm(String strValue, Class<?> valueType) {
    if (strValue.isEmpty() && valueType != String.class) {
      throw new RuntimeException("空字符串不能转换成" + valueType.getName() + "类型的值。");
    }
    if (valueType == String.class) {
      return strValue;
    } else if (valueType == Double.class) {
      return Double.parseDouble(strValue);
    } else if (valueType == Float.class) {
      return Float.parseFloat(strValue);
    } else if (valueType == Integer.class) {
      return Integer.parseInt(strValue);
    } else if (valueType == Long.class) {
      return Long.parseLong(strValue);
    } else if (valueType == Boolean.class) {
      return Boolean.parseBoolean(strValue);
    } else if (valueType == Date.class) {
      return DateUtils.parseDate(strValue);
    }
    return "";
  }

  /**
   * 截取字符串
   *
   * @param str 字符串
   * @param start 开始
   * @param end 结束
   * @return 结果
   */
  public static String substring(final String str, int start, int end) {
    if (str == null) {
      return NULLSTR;
    }

    if (end < 0) {
      end = str.length() + end;
    }
    if (start < 0) {
      start = str.length() + start;
    }

    if (end > str.length()) {
      end = str.length();
    }

    if (start > end) {
      return NULLSTR;
    }

    if (start < 0) {
      start = 0;
    }
    if (end < 0) {
      end = 0;
    }

    return str.substring(start, end);
  }

  /**
   * 格式化文本, {} 表示占位符<br> 此方法只是简单将占位符 {} 按照顺序替换为参数<br> 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符
   * \\\\ 即可<br> 例：<br> 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br> 转义{}：
   * format("this is \\{} for {}", "a", "b") -> this is \{} for a<br> 转义\： format("this is \\\\{}
   * for {}", "a", "b") -> this is \a for b<br>
   *
   * @param template 文本模板，被替换的部分用 {} 表示
   * @param params 参数值
   * @return 格式化后的文本
   */
  public static String format(String template, Object... params) {
    if (isEmpty(params) || isEmpty(template)) {
      return template;
    }
    return StrFormatter.format(template, params);
  }

  /**
   * 驼峰转下划线命名
   */
  public static String toUnderScoreCase(String str) {
    if (str == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    // 前置字符是否大写
    boolean preCharIsUpperCase = true;
    // 当前字符是否大写
    boolean curreCharIsUpperCase = true;
    // 下一字符是否大写
    boolean nexteCharIsUpperCase = true;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (i > 0) {
        preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
      } else {
        preCharIsUpperCase = false;
      }

      curreCharIsUpperCase = Character.isUpperCase(c);

      if (i < (str.length() - 1)) {
        nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
      }

      if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
        sb.append(SEPARATOR);
      } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
        sb.append(SEPARATOR);
      }
      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }

  /**
   * 是否包含字符串
   *
   * @param str 验证字符串
   * @param strs 字符串组
   * @return 包含返回true
   */
  public static boolean inStringIgnoreCase(String str, String... strs) {
    if (str != null && strs != null) {
      for (String s : strs) {
        if (str.equalsIgnoreCase(trim(s))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
   *
   * @param name 转换前的下划线大写方式命名的字符串
   * @return 转换后的驼峰式命名的字符串
   */
  public static String convertToCamelCase(String name) {
    StringBuilder result = new StringBuilder();
    // 快速检查
    if (name == null || name.isEmpty()) {
      // 没必要转换
      return "";
    } else if (!name.contains("_")) {
      // 不含下划线，仅将首字母大写
      return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    // 用下划线将原始字符串分割
    String[] camels = name.split("_");
    for (String camel : camels) {
      // 跳过原始字符串中开头、结尾的下换线或双重下划线
      if (camel.isEmpty()) {
        continue;
      }
      // 首字母大写
      result.append(camel.substring(0, 1).toUpperCase());
      result.append(camel.substring(1).toLowerCase());
    }
    return result.toString();
  }

  /**
   * 驼峰式命名法 例如：user_name->userName
   */
  public static String toCamelCase(String s) {
    if (s == null) {
      return null;
    }
    s = s.toLowerCase();
    StringBuilder sb = new StringBuilder(s.length());
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == SEPARATOR) {
        upperCase = true;
      } else if (upperCase) {
        sb.append(Character.toUpperCase(c));
        upperCase = false;
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
   *
   * @param str 指定字符串
   * @param strs 需要检查的字符串数组
   * @return 是否匹配
   */
  public static boolean matches(String str, List<String> strs) {
    if (isEmpty(str) || isEmpty(strs)) {
      return false;
    }
    for (String pattern : strs) {
      if (isMatch(pattern, str)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断url是否与规则配置: ? 表示单个字符; * 表示一层路径内的任意字符串，不可跨层级; ** 表示任意层路径;
   *
   * @param pattern 匹配规则
   * @param url 需要匹配的url
   */
  public static boolean isMatch(String pattern, String url) {
    AntPathMatcher matcher = new AntPathMatcher();
    return matcher.match(pattern, url);
  }

  @SuppressWarnings("unchecked")
  public static <T> T cast(Object obj) {
    return (T) obj;
  }

  /**
   * 将特殊符号连接的字符串替换成英文逗号
   *
   * 特殊符号包括："、，;；"，可以扩展
   *
   * @param strVal 字符对象
   * @return 替换后的字符对象
   */
  public static String replaceLinkStr(String strVal) {
    strVal =
        strVal
            .replaceAll("、", ",")
            .replaceAll("，", ",")
            .replaceAll(";", ",")
            .replaceAll("；", ",")
            .replaceAll("&", ",")
            .replaceAll("\\+", ",");
    return strVal;
  }

  /**
   * String 转 HashMap
   *
   * @param strs 输入字符
   * @return 输出HashMap
   */
  public static HashMap<String, String> stringToMap(String strs) {
    HashMap<String, String> map = null;
    try {
      Gson gson = new Gson();
      map = gson.fromJson(strs, new TypeToken<HashMap<String, String>>() {
      }.getType());
    } catch (JsonSyntaxException e) {
      log.error("{}：String转Map异常.", strs);
    }
    return map;
  }
  /**
   * 选考组合标识是否包含了某科目标识
   *
   * @param combine 选考组合标识：111000111
   * @param subjectSign 科目标识：100
   */
  public static boolean combineIsContainSign(String combine, String subjectSign) {
    if (isEmpty(combine) || isEmpty(subjectSign)) {
      return false;
    }
    if ("0".equalsIgnoreCase(combine)) {
      return true;
    }
    combine = combine.substring(combine.length() - subjectSign.length(),
        combine.length() - subjectSign.length() + 1);
    subjectSign = subjectSign.substring(0, 1);
    return combine.equalsIgnoreCase(subjectSign);
  }
}