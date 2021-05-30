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
 * 界别管理对象 std_jiebie
 *
 * @author chenyou
 * @date 2020-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_jiebie")
public class StdJiebie extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 界别主键
   */
  @TableId
  private Long jiebieId;
  /**
   * 界别名称
   */
  @Excel(name = "界别名称")
  private String jiebieName;
  /**
   * 界别代码
   */
  @Excel(name = "界别代码")
  private String jiebieCode;
  /**
   * 年级类型代码
   */
  @Excel(name = "年级类型代码")
  private String gradeTypeCode;
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
   * 逻辑删除状态
   */
  private Long delFlag;

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("jiebieId", getJiebieId())
        .append("jiebieName", getJiebieName())
        .append("jiebieCode", getJiebieCode())
        .append("gradeTypeCode", getGradeTypeCode())
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
