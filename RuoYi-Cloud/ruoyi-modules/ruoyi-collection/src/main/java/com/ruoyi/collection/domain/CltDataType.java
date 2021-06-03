package com.ruoyi.collection.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据采集类型对象 clt_data_type
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_data_type")
public class CltDataType extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long dataTypeId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String dataTypeName;

  /**
   * 代码
   */
  @Excel(name = "代码")
  private String dataTypeCode;

  /**
   * 考试模式
   */
  @Excel(name = "考试模式")
  private String examModeCode;

  /**
   * 排序
   */
  @Excel(name = "排序")
  private Long orderNum;

  /**
   * 状态
   */
  private Long status;

  /**
   * 删除标记
   */
  private Long delFlag;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("dataTypeId", getDataTypeId())
        .append("dataTypeName", getDataTypeName())
        .append("dataTypeCode", getDataTypeCode())
        .append("examModeCode", getExamModeCode())
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
