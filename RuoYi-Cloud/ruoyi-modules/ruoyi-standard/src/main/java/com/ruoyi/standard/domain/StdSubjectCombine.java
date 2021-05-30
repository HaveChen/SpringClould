package com.ruoyi.standard.domain;

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
 * 科目组合类型对象 std_subject_combine
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_subject_combine")
public class StdSubjectCombine extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long subjectCombineId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String subjectCombineName;

  /**
   * 代码
   */
  @Excel(name = "代码")
  private String subjectCombineCode;

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
   * 删除标记
   */
  private Long delFlag;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("subjectCombineId" , getSubjectCombineId())
        .append("subjectCombineName" , getSubjectCombineName())
        .append("subjectCombineCode" , getSubjectCombineCode())
        .append("orderNum" , getOrderNum())
        .append("remark" , getRemark())
        .append("status" , getStatus())
        .append("delFlag" , getDelFlag())
        .append("createBy" , getCreateBy())
        .append("createTime" , getCreateTime())
        .append("updateBy" , getUpdateBy())
        .append("updateTime" , getUpdateTime())
        .toString();
  }
}
