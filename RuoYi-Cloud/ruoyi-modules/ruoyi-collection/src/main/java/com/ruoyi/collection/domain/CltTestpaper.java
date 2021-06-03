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
 * 试卷对象 clt_testpaper
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_testpaper")
public class CltTestpaper extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long testpaperId;

  /**
   * 考试
   */
  @Excel(name = "考试")
  private Long examId;

  /**
   * 试卷名称
   */
  @Excel(name = "试卷名称")
  private String testpaperName;

  /**
   * 试卷类型
   */
  @Excel(name = "试卷类型")
  private String testpaperType;
  /**
   * 科目代码
   */
  @Excel(name = "科目代码")
  private String subjectCode;

  /**
   * 试卷满分
   */
  @Excel(name = "试卷满分")
  private Long fullscore;

  /**
   * 是否有细目表
   */
  @Excel(name = "是否有细目表")
  private Long hasItem;

  /**
   * 是否有成绩
   */
  @Excel(name = "是否有成绩")
  private Long hasCj;

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
        .append("testpaperId", getTestpaperId())
        .append("examId", getExamId())
        .append("testpaperName", getTestpaperName())
        .append("testpaperType", getTestpaperType())
        .append("fullscore", getFullscore())
        .append("hasItem", getHasItem())
        .append("hasCj", getHasCj())
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
