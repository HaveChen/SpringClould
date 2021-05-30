package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdGrade;
import com.ruoyi.standard.service.IStdGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
 * 年级Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "年级")
@RestController
@RequestMapping("/stdGrade")
public class StdGradeController extends BaseController {

  @Autowired
  private IStdGradeService stdGradeService;

  /**
   * 查询年级列表
   */
  @ApiOperation("查询年级列表")
  @PreAuthorize(hasPermi = "standard:stdGrade:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "年级", required = true) StdGrade stdGrade) {
    startPage();
    List<StdGrade> list = stdGradeService.selectStdGradeList(stdGrade);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：年级
   */
  @ApiOperation("初始化下拉数据：年级")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdGrade> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdGrade> lists = stdGradeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出年级列表
   */
  @ApiOperation("导出年级列表")
  @PreAuthorize(hasPermi = "standard:stdGrade:export")
  @Log(title = "年级", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "年级", required = true) StdGrade stdGrade) throws IOException {
    List<StdGrade> list = stdGradeService.selectStdGradeList(stdGrade);
    ExcelUtil<StdGrade> util = new ExcelUtil<StdGrade>(StdGrade.class);
     util.exportExcel(response,list, "stdGrade");
  }

  /**
   * 获取年级详细信息
   */
  @ApiOperation("获取年级详细信息")
  @PreAuthorize(hasPermi = "standard:stdGrade:query")
  @GetMapping(value = "/{gradeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "年级Id", required = true) @PathVariable("gradeId") Long gradeId) {
    return AjaxResult.success(stdGradeService.getById(gradeId));
  }

  /**
   * 新增年级
   */
  @ApiOperation("新增年级")
  @PreAuthorize(hasPermi = "standard:stdGrade:add")
  @Log(title = "年级", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdGrade stdGrade) {
    return toAjax(stdGradeService.save(stdGrade));
  }

  /**
   * 修改年级
   */
  @ApiOperation("修改年级")
  @PreAuthorize(hasPermi = "standard:stdGrade:edit")
  @Log(title = "年级", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "年级", required = true) @RequestBody StdGrade stdGrade) {
    return toAjax(stdGradeService.updateById(stdGrade));
  }

  /**
   * 删除年级
   */
  @ApiOperation("删除年级")
  @PreAuthorize(hasPermi = "standard:stdGrade:remove")
  @Log(title = "年级", businessType = BusinessType.DELETE)
  @DeleteMapping("/{gradeIds}")
  public AjaxResult remove(
      @ApiParam(value = "年级Ids", required = true) @PathVariable Long
          [] gradeIds) {
    return toAjax(stdGradeService.removeByIds(Arrays.asList(gradeIds)));
  }
}
