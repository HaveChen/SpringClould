package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租户明细对象 tenant_info_detail
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant_info_detail")
public class TenantInfoDetail extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 主键
   */
  @TableId
  private Long tenantInfoDetailId;
  /**
   * 关联
   */
  @Excel(name = "关联")
  private Long tenantInfoId;
  /**
   * 最大用户数量
   */
  @Excel(name = "最大用户数量")
  private Integer maxUserNum;
  /**
   * 同时登录用户数
   */
  @Excel(name = "同时登录用户数")
  private Integer maxLoginUserNum;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("tenantInfoDetailId", getTenantInfoDetailId())
        .append("tenantInfoId", getTenantInfoId())
        .append("maxUserNum", getMaxUserNum())
        .append("maxLoginUserNum", getMaxLoginUserNum())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .append("remark", getRemark())
        .toString();
  }
}
