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
 * 采集错误明细对象 clt_progress_detail
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_progress_detail")
public class CltProgressDetail extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long progressDetailId;

  /**
   * 采集进度
   */
  @Excel(name = "采集进度")
  private Long progressId;

  /**
   * 考试
   */
  @Excel(name = "考试")
  private Long examId;

  /**
   * 具体行
   */
  @Excel(name = "具体行")
  private Integer line;

  /**
   * 错误信息
   */
  private String errorMessage;

  /**
   * 错误类型
   */
  @Excel(name = "错误类型")
  private String errorTypeCode;

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
        .append("progressDetailId", getProgressDetailId())
        .append("progressId", getProgressId())
        .append("examId", getExamId())
        .append("line", getLine())
        .append("errorMessage", getErrorMessage())
        .append("errorTypeCode", getErrorTypeCode())
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
