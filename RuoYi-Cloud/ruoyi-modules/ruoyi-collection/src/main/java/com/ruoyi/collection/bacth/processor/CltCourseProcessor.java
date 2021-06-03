package com.ruoyi.collection.bacth.processor;

import com.google.gson.Gson;
import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.standard.api.domain.StdSubject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CltCourseProcessor implements ItemProcessor<Object, CltCourse> {

  private Map<String, CltExamDataField> filedMap;
  private String examId;
  private Map<String, StdSubject> subjectMap;

  private CltCourseProcessor() {
  }

  public CltCourseProcessor(String examId) {
    this.examId = examId;
  }

  @Override
  public CltCourse process(Object obj) {
    if (obj == null) {
      return null;
    }
    CltCourse cc = null;
    try {
      cc = new CltCourse("isImport");
      cc.setExamId(examId);
      HashMap<String, String> mapValue = (HashMap) obj;
      for (String key : filedMap.keySet()) {
        String strValue = mapValue.get(key);
        CltExamDataField etl = filedMap.get(key);
        String isExtend = etl.getIsExtend();
        //非扩展字段单独字段存储，扩展字段全部放在extensions扩展字段中（Map方式）
        if ("1".equalsIgnoreCase(isExtend)) {
          try {
            Field field = BeanUtils.findField(cc.getClass(), key);
            Object fieldValue = StringUtils.transForm(strValue, field.getType());
            BeanUtils.setField(cc, key, fieldValue);
            mapValue.remove(key);
          } catch (Exception e) {
            log.error("{}字段属性转换失败。", key, e);
          }
        }
      }
      if (!mapValue.isEmpty()) {
        BeanUtils.setField(cc, "extensions", new Gson().toJson(mapValue).toString());
      }
    } catch (Exception e) {
      log.error("处理数据出现问题,舍弃数据。", e);
      return null;
    }
    return cc;
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
