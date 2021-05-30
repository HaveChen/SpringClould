package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdCourse;
import com.ruoyi.standard.service.IStdCourseService;
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
 * 课程Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "课程")
@RestController
@RequestMapping("/stdCourse")
public class StdCourseController extends BaseController {

  @Autowired
  private IStdCourseService stdCourseService;

  /**
   * 查询课程列表
   */
  @ApiOperation("查询课程列表")
  @PreAuthorize(hasPermi = "standard:stdCourse:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "课程", required = true) StdCourse stdCourse) {
    startPage();
    List<StdCourse> list = stdCourseService.selectStdCourseList(stdCourse);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：课程
   */
  @ApiOperation("初始化下拉数据：课程")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdCourse> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdCourse> lists = stdCourseService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出课程列表
   */
  @ApiOperation("导出课程列表")
  @PreAuthorize(hasPermi = "standard:stdCourse:export")
  @Log(title = "课程", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "课程", required = true) StdCourse stdCourse) throws IOException {
    List<StdCourse> list = stdCourseService.selectStdCourseList(stdCourse);
    ExcelUtil<StdCourse> util = new ExcelUtil<StdCourse>(StdCourse.class);
     util.exportExcel(response,list, "stdCourse");
  }

  /**
   * 获取课程详细信息
   */
  @ApiOperation("获取课程详细信息")
  @PreAuthorize(hasPermi = "standard:stdCourse:query")
  @GetMapping(value = "/{courseId}")
  public AjaxResult getInfo(
      @ApiParam(value = "课程Id", required = true) @PathVariable("courseId") Long courseId) {
    return AjaxResult.success(stdCourseService.getById(courseId));
  }

  /**
   * 新增课程
   */
  @ApiOperation("新增课程")
  @PreAuthorize(hasPermi = "standard:stdCourse:add")
  @Log(title = "课程", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdCourse stdCourse) {
    return toAjax(stdCourseService.saveCourse(stdCourse));
  }

  /**
   * 修改课程
   */
  @ApiOperation("修改课程")
  @PreAuthorize(hasPermi = "standard:stdCourse:edit")
  @Log(title = "课程", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "课程", required = true) @RequestBody StdCourse stdCourse) {
    return toAjax(stdCourseService.updateCourseById(stdCourse));
  }

  /**
   * 删除课程
   */
  @ApiOperation("删除课程")
  @PreAuthorize(hasPermi = "standard:stdCourse:remove")
  @Log(title = "课程", businessType = BusinessType.DELETE)
  @DeleteMapping("/{courseIds}")
  public AjaxResult remove(
      @ApiParam(value = "课程Ids", required = true) @PathVariable Long
          [] courseIds) {
    return toAjax(stdCourseService.removeByIds(Arrays.asList(courseIds)));
  }
}
