package com.ruoyi.collection.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考试信息对象 clt_exam_info
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_exam_info")
public class CltExamInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long examId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String examName;

  /**
   * 简称
   */
  @Excel(name = "简称")
  private String examShortName;

  /**
   * 考试时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date examDate;

  /**
   * 考试类型
   */
  @Excel(name = "考试类型")
  private String examTypeCode;

  /**
   * 考试模式
   */
  @Excel(name = "考试模式")
  private String examModeCode;

  /**
   * 年级
   */
  @Excel(name = "年级")
  private String gradeCode;

  /**
   * 学年
   */
  @Excel(name = "学年")
  private String schoolYear;

  /**
   * 学年名称
   */
  @Excel(name = "学年名称")
  private String schoolYearName;

  /**
   * 界别
   */
  @Excel(name = "界别")
  private String graduateCode;

  /**
   * 学期
   */
  @Excel(name = "学期")
  private String termCode;

  /**
   * 参加考试单位
   */
  private String deptAllCode;

  /**
   * 组织单位代码
   */
  private String deptCode;

  /**
   * 组织单位Id
   */
  private Long deptId;

  /**
   * 组织单位类型
   */
  private String deptTypeCode;

  /**
   * 考生导入状态
   */
  private Long studentStatus;

  /**
   * 细目表导入情况
   */
  private Long itemCount;

  /**
   * 成绩导入情况
   */
  private Long cjCount;

  /**
   * ird等级情况
   */
  private Long irtCount;

  /**
   * 数据校验状态
   */
  private String checkStatus;

  /**
   * 考试状态
   */
  private Long examStatus;

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
        .append("examId", getExamId())
        .append("examName", getExamName())
        .append("examShortName", getExamShortName())
        .append("examDate", getExamDate())
        .append("examTypeCode", getExamTypeCode())
        .append("examModeCode", getExamModeCode())
        .append("gradeCode", getGradeCode())
        .append("schoolYear", getSchoolYear())
        .append("schoolYearName", getSchoolYearName())
        .append("graduateCode", getGraduateCode())
        .append("termCode", getTermCode())
        .append("deptAllCode", getDeptAllCode())
        .append("deptCode", getDeptCode())
        .append("deptId", getDeptId())
        .append("deptTypeCode", getDeptTypeCode())
        .append("studentStatus", getStudentStatus())
        .append("itemCount", getItemCount())
        .append("cjCount", getCjCount())
        .append("irtCount", getIrtCount())
        .append("checkStatus", getCheckStatus())
        .append("examStatus", getExamStatus())
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
