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
 * 数据采集类型--考试对象 clt_exam_data_field
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_exam_data_field")
public class CltExamDataField extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long examDataFieldId;

  /**
   * 考试主键
   */
  @Excel(name = "考试主键")
  private String examId;

  /**
   * 数据类型
   */
  @Excel(name = "数据类型")
  private String examDataTypeCode;

  /**
   * 对应实体字段
   */
  @Excel(name = "对应实体字段")
  private String fieldName;

  /**
   * 显示名称
   */
  @Excel(name = "显示名称")
  private String dataFieldName;

  /**
   * 文件对应名称
   */
  @Excel(name = "文件对应名称")
  private String defaultName;

  /**
   * 是否扩展字段 0:是 1不是
   */
  @Excel(name = "是否扩展字段")
  private String isExtend;

  /**
   * 是否必须  0:必须 1：非必须
   */
  @Excel(name = "是否必须")
  private String isNeed;

  /**
   * 对应码表
   */
  @Excel(name = "对应码表")
  private String baseTypeCode;

  /**
   * 排序
   */
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
        .append("examDataFieldId", getExamDataFieldId())
        .append("examId", getExamId())
        .append("examDataTypeCode", getExamDataTypeCode())
        .append("fieldName", getFieldName())
        .append("dataFieldName", getDataFieldName())
        .append("defaultName", getDefaultName())
        .append("isExtend", getIsExtend())
        .append("isNeed", getIsNeed())
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
