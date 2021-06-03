package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltStudentDetail;
import com.ruoyi.collection.service.ICltStudentDetailService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生信息采集Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Slf4j
@Api(tags = "学生信息采集")
@RestController
@RequestMapping("/cltStudentDetail")
public class CltStudentDetailController extends BaseController {

  @Autowired
  private ICltStudentDetailService cltStudentDetailService;

  /**
   * 查询学生信息采集列表
   */
  @ApiOperation("查询学生信息采集列表")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "学生信息采集", required = true) CltStudentDetail cltStudentDetail) {
    startPage();
    List<CltStudentDetail> list = cltStudentDetailService
        .selectCltStudentDetailList(cltStudentDetail);
    return getDataTable(list);
  }

  /**
   * 导出学生信息采集列表
   */
  @ApiOperation("导出学生信息采集列表")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:export")
  @Log(title = "学生信息采集", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "学生信息采集", required = true) CltStudentDetail cltStudentDetail)
      throws IOException {
    List<CltStudentDetail> list = cltStudentDetailService
        .selectCltStudentDetailList(cltStudentDetail);
    ExcelUtil<CltStudentDetail> util = new ExcelUtil<>(CltStudentDetail.class);
     util.exportExcel(response,list, "cltStudentDetail");
  }

  /**
   * 获取学生信息采集详细信息
   */
  @ApiOperation("获取学生信息采集详细信息")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:query")
  @GetMapping(value = "/{studentId}")
  public AjaxResult getInfo(
      @ApiParam(value = "学生信息采集Id", required = true) @PathVariable("studentId") String studentId) {
    return AjaxResult.success(cltStudentDetailService.getById(studentId));
  }

  /**
   * 新增学生信息采集
   */
  @ApiOperation("新增学生信息采集")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:add")
  @Log(title = "学生信息采集", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltStudentDetail cltStudentDetail) {
    return toAjax(cltStudentDetailService.save(cltStudentDetail));
  }

  /**
   * 修改学生信息采集
   */
  @ApiOperation("修改学生信息采集")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:edit")
  @Log(title = "学生信息采集", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "学生信息采集", required = true) @RequestBody CltStudentDetail cltStudentDetail) {
    return toAjax(cltStudentDetailService.updateById(cltStudentDetail));
  }

  /**
   * 删除学生信息采集
   */
  @ApiOperation("删除学生信息采集")
  //@PreAuthorize(hasPermi = "collection:cltStudentDetail:remove")
  @Log(title = "学生信息采集", businessType = BusinessType.DELETE)
  @DeleteMapping("/{studentIds}")
  public AjaxResult remove(
      @ApiParam(value = "学生信息采集Ids", required = true) @PathVariable String
          [] studentIds) {
    return toAjax(cltStudentDetailService.removeByIds(Arrays.asList(studentIds)));
  }

  /**
   * 删除学生信息采集
   */
  @ApiOperation("按照考试删除学生")
  @Log(title = "考试学生", businessType = BusinessType.DELETE)
  @DeleteMapping("studentDelete/{examId}")
  public AjaxResult studentDelete(
      @ApiParam(value = "考试Id", required = true) @PathVariable String examId) {
    log.debug("正在删除考试{}的学生数据。", examId);
    return toAjax(cltStudentDetailService.deleteStudentByexamId(examId));
  }
}
