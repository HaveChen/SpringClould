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
 * 试卷详情对象 clt_testpaper_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_testpaper_detail")
public class CltTestpaperDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long testpaperDetailId;

  /**
   * 试卷
   */
  @Excel(name = "试卷")
  private Long testpaperId;

  /**
   * 考试
   */
  @Excel(name = "考试")
  private Long examId;

  /**
   * 科目
   */
  @Excel(name = "科目")
  private String subjectCode;

  /**
   * 试卷满分
   */
  @Excel(name = "试卷满分")
  private Long fullscore;

  /**
   * 客观题成绩
   */
  @Excel(name = "客观题成绩")
  private Long kgscore;

  /**
   * 主观题成绩
   */
  @Excel(name = "主观题成绩")
  private Long zgscore;

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
        .append("testpaperDetailId", getTestpaperDetailId())
        .append("testpaperId", getTestpaperId())
        .append("examId", getExamId())
        .append("subjectId", getSubjectCode())
        .append("fullscore", getFullscore())
        .append("kgscore", getKgscore())
        .append("zgscore", getZgscore())
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
