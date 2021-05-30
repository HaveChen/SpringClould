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
 * 数据模型 对象 std_base_data
 *
 * @author chenyou
 * @date 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_base_data")
public class StdBaseData extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long baseDataId;
  /**
   * 名称
   */
  @Excel(name = "名称")
  private String baseDataName;
  /**
   * 默认对应值
   */
  @Excel(name = "默认对应值")
  private String defaultName;
  /**
   * 对应值
   */
  @Excel(name = "对应值")
  private String baseDataVlaue;
  /**
   * 对应类型
   */
  @Excel(name = "对应类型")
  private String baseTypeCode;
  /**
   * 排序
   */
  @Excel(name = "排序")
  private Long orderNum;
  /**
   * 状态
   */
  @Excel(name = "状态")
  private Long status;
  /**
   * 删除标记
   */
  private Long delFlag;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("baseDataId", getBaseDataId())
        .append("baseDataName", getBaseDataName())
        .append("defaultName", getDefaultName())
        .append("baseDataVlaue", getBaseDataVlaue())
        .append("baseTypeCode", getBaseTypeCode())
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
