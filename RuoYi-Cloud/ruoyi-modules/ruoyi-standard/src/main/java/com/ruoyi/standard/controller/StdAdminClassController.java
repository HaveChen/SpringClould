package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdAdminClass;
import com.ruoyi.standard.service.IStdAdminClassService;
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
 * 行政班Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "行政班")
@RestController
@RequestMapping("/stdAdminClass")
public class StdAdminClassController extends BaseController {

  @Autowired
  private IStdAdminClassService stdAdminClassService;

  /**
   * 查询行政班列表
   */
  @ApiOperation("查询行政班列表")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "行政班", required = true) StdAdminClass stdAdminClass) {
    startPage();
    List<StdAdminClass> list = stdAdminClassService.selectStdAdminClassList(stdAdminClass);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：行政班
   */
  @ApiOperation("初始化下拉数据：行政班")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdAdminClass> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdAdminClass> lists = stdAdminClassService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出行政班列表
   */
  @ApiOperation("导出行政班列表")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:export")
  @Log(title = "行政班", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "行政班", required = true) StdAdminClass stdAdminClass) throws IOException {
    List<StdAdminClass> list = stdAdminClassService.selectStdAdminClassList(stdAdminClass);
    ExcelUtil<StdAdminClass> util = new ExcelUtil<>(StdAdminClass.class);
     util.exportExcel(response,list, "stdAdminClass");
  }

  /**
   * 获取行政班详细信息
   */
  @ApiOperation("获取行政班详细信息")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:query")
  @GetMapping(value = "/{classId}")
  public AjaxResult getInfo(
      @ApiParam(value = "行政班Id", required = true) @PathVariable("classId") Long classId) {
    return AjaxResult.success(stdAdminClassService.getById(classId));
  }

  /**
   * 新增行政班
   */
  @ApiOperation("新增行政班")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:add")
  @Log(title = "行政班", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdAdminClass stdAdminClass) {
    return toAjax(stdAdminClassService.saveAdminClass(stdAdminClass));
  }

  /**
   * 修改行政班
   */
  @ApiOperation("修改行政班")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:edit")
  @Log(title = "行政班", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "行政班", required = true) @RequestBody StdAdminClass stdAdminClass) {
    return toAjax(stdAdminClassService.updateAdminClassById(stdAdminClass));
  }

  /**
   * 删除行政班
   */
  @ApiOperation("删除行政班")
  @PreAuthorize(hasPermi ="standard:stdAdminClass:remove")
  @Log(title = "行政班", businessType = BusinessType.DELETE)
  @DeleteMapping("/{classIds}")
  public AjaxResult remove(
      @ApiParam(value = "行政班Ids", required = true) @PathVariable Long
          [] classIds) {
    return toAjax(stdAdminClassService.removeByIds(Arrays.asList(classIds)));
  }
}
