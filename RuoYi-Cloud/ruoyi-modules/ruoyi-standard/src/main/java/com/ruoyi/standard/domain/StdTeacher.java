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
 * 教师对象 std_teacher
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("std_teacher")
public class StdTeacher extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long teacherId;

  /**
   * 名称
   */
  @Excel(name = "名称")
  private String teacherName;

  /**
   * 别称
   */
  @Excel(name = "别称")
  private String teacherOtherName;

  /**
   * 工号
   */
  @Excel(name = "工号")
  private String teacherCode;

  /**
   * 单位
   */
  @Excel(name = "单位")
  private String deptCode;
  private Long deptId;
  /**
   * 职称
   */
  private String teacherTitleCode;

  /**
   * 身份证
   */
  private String idcard;

  /**
   * 性别
   */
  private String gender;

  /**
   * 电话
   */
  private String mobilePhone;

  /**
   * 座机
   */
  private String landlinePhone;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 开始工作时间
   */
  private String startWorkTime;

  /**
   * 学科
   */
  private String subjectCode;

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
        .append("teacherId" , getTeacherId())
        .append("teacherName" , getTeacherName())
        .append("teacherOtherName" , getTeacherOtherName())
        .append("teacherCode" , getTeacherCode())
        .append("deptCode" , getDeptCode())
        .append("teacherTitleCode" , getTeacherTitleCode())
        .append("idcard" , getIdcard())
        .append("gender" , getGender())
        .append("mobilePhone" , getMobilePhone())
        .append("landlinePhone" , getLandlinePhone())
        .append("email" , getEmail())
        .append("startWorkTime" , getStartWorkTime())
        .append("subjectCode" , getSubjectCode())
        .append("orderNum" , getOrderNum())
        .append("status" , getStatus())
        .append("delFlag" , getDelFlag())
        .append("createBy" , getCreateBy())
        .append("createTime" , getCreateTime())
        .append("updateBy" , getUpdateBy())
        .append("updateTime" , getUpdateTime())
        .toString();
  }
}
