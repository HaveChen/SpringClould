package com.ruoyi.collection.bacth.processor;

import com.google.gson.Gson;
import com.ruoyi.collection.domain.CltCjDetail;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltTestpaper;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.standard.api.domain.StdSubject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * 读取行数据进行处理。
 *
 * <p>赋值，值替换等等。
 */
@Slf4j
public class CltCjProcessor implements ItemProcessor<Object, CltCjDetail> {

  private Map<String, CltExamDataField> filedMap;
  private CltTestpaper cltTestpaper;
  private Map<String, StdSubject> subjectMap;

  private CltCjProcessor() {
  }

  public CltCjProcessor(CltTestpaper cltTestpaper) {
    this.cltTestpaper = cltTestpaper;
  }

  @Override
  public CltCjDetail process(Object obj) {
    if (obj == null) {
      return null;
    }
    CltCjDetail cj = null;
    try {
      cj = new CltCjDetail("isImport");
      cj.setExamId(cltTestpaper.getExamId().toString());
      cj.setTestpaperId(cltTestpaper.getTestpaperId());
      cj.setSubjectCode(cltTestpaper.getSubjectCode());
      HashMap<String, String> mapValueOrig = (HashMap) obj;
      HashMap<String, String> mapValue = new HashMap<>(mapValueOrig);
      Set<String> keys = filedMap.keySet();
      for (String key : keys) {
        if (mapValue.containsKey(key)) {
          String strValue = mapValue.get(key);
          CltExamDataField etl = filedMap.get(key);
          String isExtend = etl.getIsExtend();
          //非扩展字段单独字段存储，扩展字段全部放在extensions扩展字段中（Map方式）
          if ("1".equalsIgnoreCase(isExtend)) {
            try {
              Field field = BeanUtils.findField(cj.getClass(), key);
              Object fieldValue = StringUtils.transForm(strValue, field.getType());
              BeanUtils.setField(cj, key, fieldValue);
              mapValue.remove(key);
            } catch (Exception e) {
              log.error("{}字段属性转换失败。", key, e);
            }
          }
        }
      }
      // scoreStr和omrStr加入扩展字段
      String[] extendKeys = {"scoreStr", "omrStr"};// 要加入扩展字段的key
      for (String key : extendKeys) {
        if (keys.contains(key)) {
          String strValue = mapValueOrig.get(key);
          strValue = StringUtils.replaceLinkStr(strValue);
          String[] values = strValue.split(",");
          if (values.length > 1) {
            for (int i = 0; i < values.length; i++) {
              mapValue.put(key + (i + 1), values[i]);
            }
            break;
          }
        }
      }
      if (!mapValue.isEmpty()) {
        BeanUtils.setField(cj, "extensions", new Gson().toJson(mapValue).toString());
      }
    } catch (Exception e) {
      log.error("处理数据出现问题,舍弃条数据。", e);
      return null;
    }
    return cj;
  }

  public void setFiledMap(List<CltExamDataField> cltExamDataFields) {
    this.filedMap =
        cltExamDataFields.stream()
            .collect(
                Collectors.toMap(
                    CltExamDataField::getFieldName,
                    Function.identity(),
                    (oldValue, newValue) -> newValue));
  }
}
