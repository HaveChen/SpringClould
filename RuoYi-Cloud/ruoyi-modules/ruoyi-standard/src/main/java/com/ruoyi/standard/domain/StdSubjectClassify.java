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
 * 科目所属对象 std_subject_classify
 *
 * @author chenyou
 * @date 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_subject_classify")
public class StdSubjectClassify extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 主键
   */
  @TableId
  private Long subjectClassifyId;
  /**
   * 名称
   */
  @Excel(name = "名称")
  private String subjectClassifyName;
  /**
   * 代码
   */
  @Excel(name = "代码")
  private String subjectClassifyCode;
  /**
   * 排序
   */
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
        .append("subjectClassifyId", getSubjectClassifyId())
        .append("subjectClassifyName", getSubjectClassifyName())
        .append("subjectClassifyCode", getSubjectClassifyCode())
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
