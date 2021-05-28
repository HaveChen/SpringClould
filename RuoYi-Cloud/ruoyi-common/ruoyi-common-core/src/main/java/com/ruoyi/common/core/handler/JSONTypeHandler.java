package com.ruoyi.common.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.utils.StringUtils;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/*
 * 数据库的字符串转换为JSONObject对象
 */
public class JSONTypeHandler extends BaseTypeHandler<JSONObject> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter,
      JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.toJSONString());
  }

  @Override
  public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String dataStr = rs.getString(columnName);
    if (StringUtils.isNotEmpty(dataStr)) {
      return JSONObject.parseObject(dataStr);
    }
    return null;
  }

  @Override
  public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String dataStr = rs.getString(columnIndex);
    if (StringUtils.isNotEmpty(dataStr)) {
      return JSONObject.parseObject(dataStr);
    }
    return null;
  }

  @Override
  public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String dataStr = cs.getString(columnIndex);
    if (StringUtils.isNotEmpty(dataStr)) {
      return JSONObject.parseObject(dataStr);
    }
    return null;
  }
}
