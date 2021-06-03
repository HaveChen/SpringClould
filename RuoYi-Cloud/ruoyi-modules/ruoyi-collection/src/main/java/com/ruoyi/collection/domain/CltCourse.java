package com.ruoyi.collection.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 采集课程对象 clt_course
 *
 * @author chenyou
 * @date 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_course")
public class CltCourse extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(type = IdType.ASSIGN_UUID)
  private String courseId;

  /**
   * 考试
   */
  @Excel(name = "考试")
  private String examId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String courseName;

  /**
   * 代码
   */
  @Excel(name = "代码")
  private String courseCode;

  /**
   * 教师
   */
  @Excel(name = "教师")
  private String teacherCode;

  /**
   * 学科
   */
  @Excel(name = "学科")
  private String subjectCode;

  /**
   * 教学班
   */
  @Excel(name = "教学班")
  private String teachClassCode;

  /**
   * 单位代码
   */
  @Excel(name = "单位代码")
  private String deptCode;

  /**
   * 扩展字段
   */
  private String extensions;

  /**
   * 单位 关联sys_dept
   */
  private Long deptId;

  /**
   * 届别
   */
  private String graduateCode;

  /**
   * 学年
   */
  private String schoolYearCode;

  /**
   * 年级
   */
  private String gradeCode;

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
  /**
   * 是否是导入时，用在导入插入数据，获取不到user，临时方案，后期改进
   */
  @TableField(exist = false)
  private String isImport;

  public CltCourse() {
  }

  public CltCourse(String isImport) {
    this.isImport = isImport;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("courseId", getCourseId())
        .append("examId", getExamId())
        .append("courseName", getCourseName())
        .append("courseCode", getCourseCode())
        .append("teacherCode", getTeacherCode())
        .append("subjectCode", getSubjectCode())
        .append("teachClassCode", getTeachClassCode())
        .append("deptCode", getDeptCode())
        .append("deptId", getDeptId())
        .append("graduateCode", getGraduateCode())
        .append("schoolYearCode", getSchoolYearCode())
        .append("gradeCode", getGradeCode())
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
