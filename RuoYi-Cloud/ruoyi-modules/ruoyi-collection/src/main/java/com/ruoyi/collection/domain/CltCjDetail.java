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
 * 成绩数据采集对象 clt_cj_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_cj_detail")
public class CltCjDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(type = IdType.ASSIGN_UUID)
  private String cjId;

  /**
   * 考试
   */
  private String examId;

  /**
   * 试卷
   */
  @Excel(name = "试卷")
  private Long testpaperId;

  /**
   * 科目
   */
  @Excel(name = "科目")
  private String subjectCode;

  /**
   * 准考证号
   */
  @Excel(name = "准考证号")
  private String studentZkzh;

  /**
   * 班级
   */
  @Excel(name = "班级")
  private String classCode;

  /**
   * 学校
   */
  @Excel(name = "学校")
  private String deptCode;

  /**
   * 所有上级代码
   */
  private String parentAllCode;

  /**
   * 成绩标识
   */
  @Excel(name = "成绩标识")
  private String cjSign;

  /**
   * 全卷总分
   */
  @Excel(name = "全卷总分")
  private String totalScore;

  /**
   * 主观题总分
   */
  @Excel(name = "主观题总分")
  private String zgScore;
  /**
   * 客观题总分
   */
  @Excel(name = "客观题总分")
  private String kgScore;

  /**
   * 客观题选择串
   */
  @Excel(name = "客观题选择串")
  private String omrStr;
  /**
   * 客观题得分串
   */
  @Excel(name = "客观题得分串")
  private String scoreStr;
  /**
   * 扩展字段
   */
  @Excel(name = "扩展字段")
  private String extensions;

  /**
   * 状态
   */
  private Long status;

  /**
   * 删除标记
   */
  private Long delFlag;

  private Integer orderNum;
  /**
   * 是否是导入时，用在导入插入数据，获取不到user，临时方案，后期改进
   */
  @TableField(exist = false)
  private String isImport;

  public CltCjDetail() {
  }

  public CltCjDetail(String isImport) {
    this.isImport = isImport;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("cjId", getCjId())
        .append("examId", getExamId())
        .append("testpaperId", getTestpaperId())
        .append("subjectCode", getSubjectCode())
        .append("studentZkzh", getStudentZkzh())
        .append("classCode", getClassCode())
        .append("deptCode", getDeptCode())
        .append("parentAllCode", getParentAllCode())
        .append("cjSign", getCjSign())
        .append("totalScore", getTotalScore())
        .append("kgScore", getKgScore())
        .append("zgScore", getZgScore())
        .append("extensions", getExtensions())
        .append("status", getStatus())
        .append("delFlag", getDelFlag())
        .append("createBy", getCreateBy())
        .append("createTime", getCreateTime())
        .append("updateBy", getUpdateBy())
        .append("updateTime", getUpdateTime())
        .toString();
  }
}
