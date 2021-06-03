package com.ruoyi.collection.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 细目信息采集对象 clt_item_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_item_detail")
public class CltItemDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(type = IdType.ASSIGN_UUID)
  private String itemId;

  /**
   * 考试主键
   */
  @Excel(name = "考试主键")
  private String examId;

  /**
   * 试卷主键
   */
  @Excel(name = "试卷主键")
  private String testpaperId;

  /**
   * 科目代码
   */
  @Excel(name = "科目代码")
  private String subjectCode;

  /**
   * 给分点
   */
  @Excel(name = "给分点")
  private String scorePoint;

  /**
   * 小题号
   */
  @Excel(name = "小题号")
  private String littleTitleNo;

  /**
   * 大题号
   */
  @Excel(name = "大题号")
  private String bigTitleNo;

  /**
   * 给分点类型
   */
  @Excel(name = "给分点类型")
  private String scorePointType;

  /**
   * 满分
   */
  @Excel(name = "满分")
  private String fullscore;

  /**
   * 全部选项
   */
  @Excel(name = "全部选项")
  private String allOptions;

  /**
   * 正确选项
   */
  @Excel(name = "正确选项")
  private String rightOptioin;

  /**
   * 预测难度
   */
  @Excel(name = "预测难度")
  private String forecastDifficulty;

  /**
   * 是否选做题
   */
  @Excel(name = "是否选做题")
  private String isChoice;

  /**
   * 选做题组
   */
  @Excel(name = "选做题组 ")
  private String choiceGroup;

  /**
   * 选做题规则
   */
  @Excel(name = "选做题规则")
  private String choiceRule;

  /**
   * 成绩对应字段
   */
  @Excel(name = "成绩对应字段")
  private String cjField;

  /**
   * 扩展字段
   */
  @Excel(name = "扩展字段")
  private String extensions;

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
        .append("itemId", getItemId())
        .append("examId", getExamId())
        .append("testpaperId", getTestpaperId())
        .append("subjectCode", getSubjectCode())
        .append("scorePoint", getScorePoint())
        .append("littleTitleNo", getLittleTitleNo())
        .append("bigTitleNo", getBigTitleNo())
        .append("scorePointType", getScorePointType())
        .append("fullscore", getFullscore())
        .append("allOptions", getAllOptions())
        .append("rightOptioin", getRightOptioin())
        .append("forecastDifficulty", getForecastDifficulty())
        .append("isChoice", getIsChoice())
        .append("choiceGroup", getChoiceGroup())
        .append("choiceRule", getChoiceRule())
        .append("cjField", getCjField())
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
