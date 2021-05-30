package com.ruoyi.standard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 科目管理对象 std_subject
 *
 * @author chenyou
 * @date 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_subject")
public class StdSubject extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 科目Id
   */
  @TableId
  private Long subjectId;
  /**
   * 科目编码（shuxue，yuwen）
   */
  @Excel(name = "科目编码", readConverterExp = "s=huxue，yuwen")
  private String subjectCode;
  /**
   * 科目名称
   */
  @Excel(name = "科目名称")
  private String subjectName;
  /**
   * 科目别名
   */
  @Excel(name = "科目别名")
  private String subjectOtherName;
  /**
   * 科目类型（必选，首选）
   */
  @Excel(name = "科目类型", readConverterExp = "必=选，首选")
  private String subjectTypeCode;
  /**
   * 科目标识（1，10，100）
   */
  @Excel(name = "科目标识", readConverterExp = "1=，10，100")
  private Long subjectSign;
  /**
   * 科目分类
   */
  @Excel(name = "科目分类")
  private String subjectClassifyCode;
  /**
   * 排序
   */
  @Excel(name = "排序")
  private Long orderNum;
  /**
   * 状态
   */
  @Excel(name = "状态")
  private Integer status;
  /**
   * 逻辑删除状态
   */
  private Long delFlag;

  /**
   * 科目类型
   */
  @TableField(exist = false)
  private StdSubjectType stdSubjectType;

  /**
   * 科目所属
   */
  @TableField(exist = false)
  private StdSubjectClassify stdSubjectClassify;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("subjectId", getSubjectId())
        .append("subjectCode", getSubjectCode())
        .append("subjectName", getSubjectName())
        .append("subjectOtherName", getSubjectOtherName())
        .append("subjectTypeCode", getSubjectTypeCode())
        .append("subjectSign", getSubjectSign())
        .append("subjectClassifyCode", getSubjectClassifyCode())
        .append("orderNum", getOrderNum())
        .append("status", getStatus())
        .append("delFlag", getDelFlag())
        .append("remark", getRemark())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .toString();
  }
}
