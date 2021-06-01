package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 多租户对象 tenant_info
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant_info")
public class TenantInfo extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 主键
   */
  @TableId
  private Long tenantInfoId;
  /**
   * 租户id
   */
  @Excel(name = "租户id")
  private Long tenantId;
  /**
   * 租户名称
   */
  @Excel(name = "租户名称")
  private String tenantName;
  /**
   * 状态
   */
  @Excel(name = "状态")
  private Integer status;
  /**
   * 过期时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date overTime;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("tenantInfoId", getTenantInfoId())
        .append("tenantId", getTenantId())
        .append("tenantName", getTenantName())
        .append("status", getStatus())
        .append("overTime", getOverTime())
        .append("createTime", getCreateTime())
        .append("createBy", getCreateBy())
        .append("updateTime", getUpdateTime())
        .append("updateBy", getUpdateBy())
        .append("remark", getRemark())
        .toString();
  }
}
