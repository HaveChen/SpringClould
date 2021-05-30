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
 * 班级管理对象 std_class
 *
 * @author chenyou
 * @date 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_class")
public class StdClass extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 班级主键
   */
  @TableId
  private Long classId;
  /**
   * 班级名称
   */
  @Excel(name = "班级名称")
  private String className;
  /**
   * 班级代码
   */
  @Excel(name = "班级代码")
  private String classCode;
  /**
   * 班级类型
   */
  @Excel(name = "班级类型")
  private String classTypeCode;
  /**
   * 班级属性
   */
  @Excel(name = "班级属性")
  private String classAttributeCode;
  /**
   * 所属单位代码
   */
  @Excel(name = "所属单位代码")
  private String deptCode;
  /**
   * 界别代码
   */
  @Excel(name = "界别代码")
  private String jiebieCode;
  /**
   * 年级代码
   */
  @Excel(name = "年级代码")
  private String gradeCode;
  /**
   * 学期代码
   */
  @Excel(name = "学期代码")
  private String termCode;
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
   * 逻辑删除状态
   */
  private Long delFlag;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("classId", getClassId())
        .append("className", getClassName())
        .append("classCode", getClassCode())
        .append("classTypeCode", getClassTypeCode())
        .append("classAttributeCode", getClassAttributeCode())
        .append("deptCode", getDeptCode())
        .append("jiebieCode", getJiebieCode())
        .append("gradeCode", getGradeCode())
        .append("termCode", getTermCode())
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
