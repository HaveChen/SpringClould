package com.ruoyi.standard.domain;

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
 * 数据类型 对象 std_base_type
 *
 * @author chenyou
 * @date 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_base_type")
public class StdBaseType extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long baseTypeId;
  /**
   * 名称
   */
  @Excel(name = "名称")
  private String baseTypeName;
  /**
   * 代码
   */
  @Excel(name = "代码")
  private String baseTypeCode;
  /**
   * 父级
   */
  @Excel(name = "父级")
  @TableId
  private String parentId;
  /**
   * 是否最下级
   */
  @Excel(name = "是否最下级")
  private Long isLowest;
  /**
   * 排序
   */
  @Excel(name = "排序")
  private Long orderNum;
  /**
   * 状态
   */
  @Excel(name = "状态 ")
  private Long status;
  /**
   * 删除标记
   */
  private Long delFlag;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("baseTypeId", getBaseTypeId())
        .append("baseTypeName", getBaseTypeName())
        .append("baseTypeCode", getBaseTypeCode())
        .append("parentId", getParentId())
        .append("isLowest", getIsLowest())
        .append("orderNum", getOrderNum())
        .append("remark", getRemark())
        .append("status", getStatus())
        .append("delFlag", getDelFlag())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .toString();
  }
}
