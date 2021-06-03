package com.ruoyi.collection.domain;

import com.alibaba.fastjson.JSONObject;
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
 * 采集进度对象 clt_progress
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clt_progress")
public class CltProgress extends BaseEntity {

  private static final long serialVersionUID = 1L;


  public CltProgress(String fileName, String filePath) {
    this.fileName = fileName;
    this.filePath = filePath;
  }

  private CltProgress() {
  }

  /**
   * 主键
   */
  @TableId
  private Long progressId;

  /**
   * 考试
   */
  @Excel(name = "考试")
  private Long examId;
  /**
   * 文件路径
   */
  private String filePath;

  /**
   * 文件名称
   */
  @Excel(name = "文件名称")
  private String fileName;
  /**
   * 文件类型
   */
  private String fileType;

  /**
   * 识别类型
   */
  @Excel(name = "识别类型")
  private String importType;

  /**
   * 科目代码
   */
  private String subjectCode;

  /**
   * 字段匹配
   */
  @Excel(name = "字段匹配")
  private String fieldMapping;

  /**
   * 文件所有列
   */
  private String allField;

  /**
   * 总行数
   */
  @Excel(name = "总行数")
  private int sumLine;

  /**
   * 成功行数
   */
  @Excel(name = "成功行数")
  private int sucessLine;

  /**
   * 失败行数
   */
  @Excel(name = "失败行数")
  private int errorLine;

  /**
   * 采集状态
   *
   * 0：上传匹配失败，1：匹配成功，2：等待导入 3：正在导入 4：导入完成
   */
  @Excel(name = "采集状态")
  private String importStatus;

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

  @TableField(exist = false)
  private JSONObject params;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("progressId", getProgressId())
        .append("examId", getExamId())
        .append("fileName", getFileName())
        .append("importType", getImportType())
        .append("fieldMapping", getFieldMapping())
        .append("sumLine", getSumLine())
        .append("sucessLine", getSucessLine())
        .append("errorLine", getErrorLine())
        .append("importStatus", getImportStatus())
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
