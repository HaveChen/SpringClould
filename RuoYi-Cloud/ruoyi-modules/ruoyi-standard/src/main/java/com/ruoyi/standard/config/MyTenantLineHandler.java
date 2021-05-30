package com.ruoyi.standard.config;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import java.util.Arrays;
import java.util.List;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

public class MyTenantLineHandler implements TenantHandler {

  private static List<String> tableNameList;

  static {
    //初始化不需要多租户过滤的表
    tableNameList = Arrays
        .asList("std_admin_class", "std_class_type", "std_course",
            "std_grade", "std_grade_leader", "std_graduate", "std_school_year", "std_study_level",
            "std_subject", "std_subject_classify", "std_subject_combine", "std_subject_type",
            "std_teach_class", "std_teacher", "std_teacher_title", "std_term");
  }

  @Override
  public Expression getTenantId(boolean where) {
    //获取当前用户的租户Id
    if (!where) {
      return new StringValue("1");
    }
    return new StringValue("");
  }

  //租户过滤字段
  @Override
  public String getTenantIdColumn() {
    return "tenant_id";
  }

  //此处可以配置忽略多租户查询的表 true 表示不过滤
  @Override
  public boolean doTableFilter(String tableName) {
    if (tableNameList.contains(tableName)) {
      return true;
    }
    return false;
  }
}
