package com.ruoyi.common.core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

/**
 * Map通用处理方法
 *
 * @author ruoyi
 */
public class MapDataUtils {

  public static Map<String, Object> convertDataMap(HttpServletRequest request) {
    Map<String, String[]> properties = request.getParameterMap();
    Map<String, Object> returnMap = new HashMap<String, Object>();
    Iterator<?> entries = properties.entrySet().iterator();
    Entry<?, ?> entry;
    String name = "";
    String value = "";
    while (entries.hasNext()) {
      entry = (Entry<?, ?>) entries.next();
      name = (String) entry.getKey();
      Object valueObj = entry.getValue();
      if (null == valueObj) {
        value = "";
      } else if (valueObj instanceof String[]) {
        String[] values = (String[]) valueObj;
        for (int i = 0; i < values.length; i++) {
          value = values[i] + ",";
        }
        value = value.substring(0, value.length() - 1);
      } else {
        value = valueObj.toString();
      }
      returnMap.put(name, value);
    }
    return returnMap;
  }

  /**
   * Map排序：根据key排序
   *
   * @param maps Map<String, String>
   */
  public static Map<String, String> sortByKey(Map<String, String> maps) {
    Map<String, String> result = new LinkedHashMap<>();
    maps.entrySet().stream()
        .sorted(Entry.comparingByKey())
        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    return result;
  }

  /**
   * Map排序：根据key排序:默认升序
   *
   * @param maps Map<Double, Integer>
   */
  public static Map<Double, Integer> sortByDoubleKey(Map<Double, Integer> maps) {

    return sortByDoubleKey(maps, "asc");
  }

  /**
   * Map排序：根据key排序,按照way升序或者降序
   *
   * @param maps Map<Double, Integer>
   * @param way asc,desc
   */
  public static Map<Double, Integer> sortByDoubleKey(Map<Double, Integer> maps, String way) {
    Map<Double, Integer> result = new LinkedHashMap<>();
    //way 为空默认升序
    if (StringUtils.isEmpty(way) || "asc".equalsIgnoreCase(way)) {
      maps.entrySet().stream()
          .sorted(Entry.comparingByKey())
          .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    } else {
      maps.entrySet().stream()
          .sorted((Entry.<Double, Integer>comparingByKey().reversed()))
          .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    }
    return result;
  }

  /**
   * Map排序：根据Value排序：降序
   *
   * @param maps Map<String, String>
   */
  public static Map<String, String> sortByValue(Map<String, String> maps) {
    Map<String, String> result = new LinkedHashMap<>();
    maps.entrySet().stream()
        .sorted(Entry.<String, String>comparingByValue().reversed())
        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    return result;
  }

  /**
   * Map排序：根据Value排序,按照way升序或者降序
   *
   * @param maps Map<Double, Integer>
   * @param way asc 升序,desc 降序
   */
  public static Map<Double, Integer> sortByDoubleValue(Map<Double, Integer> maps, String way) {
    Map<Double, Integer> result = new LinkedHashMap<>();
    if (StringUtils.isEmpty(way) || "asc".equalsIgnoreCase(way)) {
      maps.entrySet().stream()
          .sorted(Entry.comparingByValue())
          .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    } else {
      maps.entrySet().stream()
          .sorted(Entry.<Double, Integer>comparingByValue().reversed())
          .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    }
    return result;
  }

  /**
   * Map排序：根据Value排序:默认升序
   *
   * @param maps Map<Double, Integer>
   */
  public static Map<Double, Integer> sortByDoubleValue(Map<Double, Integer> maps) {
    return sortByDoubleValue(maps, "asc");
  }

  /**
   * @param str 必须是Map<String, Integer>
   * @return Map<String, Integer>
   */
  public static Map<String, Integer> stringToMap(String str) {
    if (StringUtils.isEmpty(str)) {
      return null;
    }
    Gson gson = new Gson();
    return gson.fromJson(str, new TypeToken<Map<String, Integer>>() {
    }.getType());
  }

}
