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
 * 采集扩展维度明细对象 clt_extension_field_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_extension_field_detail")
public class CltExtensionFieldDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long extensionDetailId;

  /**
   * 扩展字段主键
   */
  @Excel(name = "扩展字段主键")
  private Long extensionId;

  /**
   * 考试主键
   */
  @Excel(name = "考试主键")
  private Long examId;

  /**
   * 科目
   */
  @Excel(name = "科目")
  private String subjectCode;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String extensionDetaiName;

  /**
   * 代码
   */
  @Excel(name = "代码")
  private String extensionDetaiCode;

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
        .append("extensionDetailId", getExtensionDetailId())
        .append("extensionId", getExtensionId())
        .append("examId", getExamId())
        .append("subjectCode", getSubjectCode())
        .append("extensionDetaiName", getExtensionDetaiName())
        .append("extensionDetaiCode", getExtensionDetaiCode())
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
