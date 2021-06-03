package com.ruoyi.collection.bacth.processor;

import com.google.gson.Gson;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltStudentDetail;
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
import org.springframework.batch.item.ItemProcessor;

/**
 * 读取行数据进行处理。
 *
 * <p>赋值，值替换等等。
 */
public class CltStudentProcessor implements ItemProcessor<Object, CltStudentDetail> {

  private Map<String, CltExamDataField> filedMap;
  private String examId;
  private Map<String, StdSubject> subjectMap;

  private CltStudentProcessor() {
  }

  public CltStudentProcessor(String examId) {
    this.examId = examId;
  }

  @Override
  public CltStudentDetail process(Object obj) {
    if (obj == null) {
      return null;
    }
    CltStudentDetail stu = null;
    try {
      stu = new CltStudentDetail("isImport");
      stu.setExamId(examId);
      HashMap<String, String> mapValue = (HashMap) obj;
      Set<String> keys = filedMap.keySet();
      for (String key : keys) {
        if (mapValue.containsKey(key)) {
          String strValue = mapValue.get(key);
          CltExamDataField etl = filedMap.get(key);
          String isExtend = etl.getIsExtend();
          //非扩展字段单独字段存储，扩展字段全部放在extensions扩展字段中（Map方式）
          if ("1".equalsIgnoreCase(isExtend)) {
            Field field = BeanUtils.findField(stu.getClass(), key);
            Object fieldValue = StringUtils.transForm(strValue, field.getType());
            BeanUtils.setField(stu, key, fieldValue);
            mapValue.remove(key);
          }
          specialField(stu, key, strValue);
        }
      }
      if (!mapValue.isEmpty()) {
        BeanUtils.setField(stu, "extensions", new Gson().toJson(mapValue).toString());
      }
    } catch (Exception e) {
      //TODO
    }
    return stu;
  }

  /**
   * 特殊字段处理(数据转换或替换)
   */
  private void specialField(CltStudentDetail stu, String fieldName, String fieldValue) {
    // 考生类型根据选择科目进行初始值
    if ("subjects".equalsIgnoreCase(fieldName)) {
      makeSubjects(stu, fieldValue);
    } else if ("courseCodes".equalsIgnoreCase(fieldName)) {
      makeCourseCodes(stu, fieldValue);
    }
  }

  /**
   * 考试课程转换
   */
  private void makeCourseCodes(CltStudentDetail stu, String fieldValue) {
    if (StringUtils.isEmpty(fieldValue)) {
      return;
    }
    HashMap<String, String> map = new HashMap<>();
    fieldValue = StringUtils.replaceLinkStr(fieldValue);
    String[] strs = fieldValue.split(",");
    for (String str : strs) {
      map.put(str, str);
    }
    if (map.size() > 0) {
      stu.setCourseCodes(new Gson().toJson(map).toString());
    }
  }

  /**
   * 考生类型根据选择科目进行初始值
   */
  private void makeSubjects(CltStudentDetail stu, String fieldValue) {
    // 普通文理标识(暂时不根据考试类型区处理文理)
    if (makeWlTpe(stu, fieldValue)) {
      return;
    }
    // 拼接的特殊字符：中英文逗号，中英文分号，顿号
    fieldValue = StringUtils.replaceLinkStr(fieldValue);
    String[] strs = fieldValue.split(",");
    Long sum = 0L;
    for (String str : strs) {
      if (subjectMap.containsKey(str)) {
        StdSubject su = subjectMap.get(str);
        sum += su.getSubjectSign();
      } else {
        stu.setStudentCombineCode("-999");
        break;
      }
      stu.setStudentCombineCode("" + sum);
    }
  }

  /**
   * 处理学生普通文理类型
   */
  private boolean makeWlTpe(CltStudentDetail stu, String fieldValue) {
    if ("0".equalsIgnoreCase(fieldValue)
        || "1".equalsIgnoreCase(fieldValue)
        || "2".equalsIgnoreCase(fieldValue)) {
      stu.setStudentCombineCode(fieldValue);
      return true;
    } else if ("不分文理".equalsIgnoreCase(fieldValue) || "不分".equalsIgnoreCase(fieldValue)) {
      stu.setStudentCombineCode("0");
      return true;
    } else if ("文科".equalsIgnoreCase(fieldValue) || "文".equalsIgnoreCase(fieldValue)) {
      stu.setStudentCombineCode("1");
      return true;
    } else if ("理科".equalsIgnoreCase(fieldValue) || "理".equalsIgnoreCase(fieldValue)) {
      stu.setStudentCombineCode("2");
      return true;
    }
    return false;
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

  public void setStujectMap(List<StdSubject> subjects) {
    this.subjectMap =
        subjects.stream()
            .collect(
                Collectors.toMap(
                    StdSubject::getSubjectName,
                    Function.identity(),
                    (oldValue, newValue) -> newValue));
  }
}
