package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.service.ICltCourseService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
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
 * 采集课程Controller
 *
 * @author chenyou
 * @date 2021-02-26
 */
@Api(tags = "采集课程")
@RestController
@RequestMapping("/cltCourse")
public class CltCourseController extends BaseController {

  @Autowired
  private ICltCourseService cltCourseService;

  /**
   * 查询采集课程列表
   */
  @ApiOperation("查询采集课程列表")
  @PreAuthorize(hasPermi = "collection:cltCourse:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "采集课程", required = true) CltCourse cltCourse) {
    startPage();
    List<CltCourse> list = cltCourseService.selectCltCourseList(cltCourse);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：采集课程
   */
  @ApiOperation("初始化下拉数据：采集课程")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltCourse> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltCourse> lists = cltCourseService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出采集课程列表
   */
  @ApiOperation("导出采集课程列表")
  @PreAuthorize(hasPermi = "collection:cltCourse:export")
  @Log(title = "采集课程", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "采集课程", required = true) CltCourse cltCourse) throws IOException {
    List<CltCourse> list = cltCourseService.selectCltCourseList(cltCourse);
    ExcelUtil<CltCourse> util = new ExcelUtil<CltCourse>(CltCourse.class);
     util.exportExcel(response,list, "cltCourse");
  }

  /**
   * 获取采集课程详细信息
   */
  @ApiOperation("获取采集课程详细信息")
  @PreAuthorize(hasPermi = "collection:cltCourse:query")
  @GetMapping(value = "/{courseId}")
  public AjaxResult getInfo(
      @ApiParam(value = "采集课程Id", required = true) @PathVariable("courseId") String courseId) {
    return AjaxResult.success(cltCourseService.getById(courseId));
  }

  /**
   * 新增采集课程
   */
  @ApiOperation("新增采集课程")
  @PreAuthorize(hasPermi = "collection:cltCourse:add")
  @Log(title = "采集课程", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltCourse cltCourse) {
    return toAjax(cltCourseService.save(cltCourse));
  }

  /**
   * 修改采集课程
   */
  @ApiOperation("修改采集课程")
  @PreAuthorize(hasPermi = "collection:cltCourse:edit")
  @Log(title = "采集课程", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "采集课程", required = true) @RequestBody CltCourse cltCourse) {
    return toAjax(cltCourseService.updateById(cltCourse));
  }

  /**
   * 删除采集课程
   */
  @ApiOperation("删除采集课程")
  @PreAuthorize(hasPermi = "collection:cltCourse:remove")
  @Log(title = "采集课程", businessType = BusinessType.DELETE)
  @DeleteMapping("/{courseIds}")
  public AjaxResult remove(
      @ApiParam(value = "采集课程Ids", required = true) @PathVariable String
          [] courseIds) {
    return toAjax(cltCourseService.removeByIds(Arrays.asList(courseIds)));
  }
}
