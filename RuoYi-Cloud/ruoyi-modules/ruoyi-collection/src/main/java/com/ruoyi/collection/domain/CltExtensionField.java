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
 * 采集扩展维度对象 clt_extension_field
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_extension_field")
public class CltExtensionField extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long extensionId;

  /**
   * 考试主键
   */
  @Excel(name = "考试主键")
  private Long examId;

  /**
   * 试卷主键
   */
  @Excel(name = "试卷主键")
  private Long testpaerId;

  /**
   * 科目主键
   */
  @Excel(name = "科目主键")
  private String subjectCode;

  /**
   * 扩展字段名称
   */
  @Excel(name = "扩展字段名称")
  private String extensionName;

  /**
   * 扩展字段代码
   */
  @Excel(name = "扩展字段代码")
  private String extensionCode;

  /**
   * 对应码表
   */
  @Excel(name = "对应码表")
  private String basicCodeTable;

  /**
   * 扩展字段类型
   */
  @Excel(name = "扩展字段类型")
  private String extensionType;

  /**
   * 所属对象
   */
  @Excel(name = "所属对象")
  private String belongType;

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
        .append("extensionId", getExtensionId())
        .append("examId", getExamId())
        .append("testpaerId", getTestpaerId())
        .append("subjectCode", getSubjectCode())
        .append("extensionName", getExtensionName())
        .append("extensionCode", getExtensionCode())
        .append("basicCodeTable", getBasicCodeTable())
        .append("extensionType", getExtensionType())
        .append("belongType", getBelongType())
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
