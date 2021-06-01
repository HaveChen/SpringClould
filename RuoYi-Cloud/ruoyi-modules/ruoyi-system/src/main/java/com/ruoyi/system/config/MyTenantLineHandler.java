package com.ruoyi.system.config;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.ruoyi.common.core.utils.SecurityUtils;
import java.util.Arrays;
import java.util.List;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

public class MyTenantLineHandler implements TenantHandler {

  private static List<String> tableNameList;

  static {
    //初始化不需要租户过滤的表
    tableNameList = Arrays
        .asList("sys_config", "sys_user",
            "sys_dept", "sys_dict_data", "sys_dict_type", "sys_job", "sys_job_log",
            "sys_menu", "sys_notice", "sys_role_dept", "sys_role_menu", "sys_user_post",
            "sys_user_role", "tenant_info", "tenant_info_detail");
  }

  @Override
  public Expression getTenantId(boolean where) {
    //获取当前用户的租户Id
    if (!where) {
      String tenantId = SecurityUtils.getTenantId();
      return new StringValue(tenantId);
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
