package com.ruoyi.common.core.handler;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import java.util.Arrays;
import java.util.List;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

public class MyTenantLineHandler implements TenantHandler {

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

  //此处可以配置忽略多租户查询的表
  @Override
  public boolean doTableFilter(String tableName) {
    List<String> tableNameList = Arrays.asList("sys_role");
    if (tableNameList.contains(tableName)) {
      return false;
    }
    return true;
  }
}
