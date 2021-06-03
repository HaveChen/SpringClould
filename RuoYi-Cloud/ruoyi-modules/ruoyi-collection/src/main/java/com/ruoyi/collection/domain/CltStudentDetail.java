package com.ruoyi.collection.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.BaseEntity;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学生信息采集对象 clt_student_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_student_detail")
public class CltStudentDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(type = IdType.ASSIGN_UUID)
  private String studentId;

  /**
   * 考试主键
   */
  @Excel(name = "考试主键")
  private String examId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String studentName;

  /**
   * 学号
   */
  @Excel(name = "学号")
  private String studentNum;

  /**
   * 准考证号
   */
  @Excel(name = "准考证号")
  private String studentZkzh;

  /**
   * 选考类型
   */
  @Excel(name = "选考类型")
  private String studentCombineCode;

  /**
   * 外语科目
   */
  @Excel(name = "外语科目")
  private String foreignSubjectCode;

  /**
   * 选考科目名称
   */
  @Excel(name = "选考科目名称")
  private String subjects;

  /**
   * 班级
   */
  @Excel(name = "班级")
  private String classCode;

  /**
   * 学校
   */
  @Excel(name = "学校")
  private String schoolCode;

  /**
   * 所有上级代码
   */
  private String parentsCode;

  /**
   * 扩展字段 Map
   */
  private String extensions;
  /**
   * 课程信息
   */
  private String courseCodes;

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
  /**
   * 是否是导入时，用在导入插入数据，获取不到user，临时方案，后期改进
   */
  @TableField(exist = false)
  private String isImport;

  public CltStudentDetail() {
  }

  public CltStudentDetail(String isImport) {
    this.isImport = isImport;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("studentId", getStudentId())
        .append("examId", getExamId())
        .append("studentName", getStudentName())
        .append("studentNum", getStudentNum())
        .append("studentZkzh", getStudentZkzh())
        .append("studentCombineCode", getStudentCombineCode())
        .append("foreignSubjectCode", getForeignSubjectCode())
        .append("subjects", getSubjects())
        .append("classCode", getClassCode())
        .append("schoolCode", getSchoolCode())
        .append("parentsCode", getParentsCode())
        .append("extensions", getExtensions())
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
