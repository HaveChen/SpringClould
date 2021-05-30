package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdTeacher;
import com.ruoyi.standard.service.IStdTeacherService;
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
 * 教师Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "教师")
@RestController
@RequestMapping("/stdTeacher")
public class StdTeacherController extends BaseController {

  @Autowired
  private IStdTeacherService stdTeacherService;

  /**
   * 查询教师列表
   */
  @ApiOperation("查询教师列表")
  @PreAuthorize(hasPermi = "standard:stdTeacher:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "教师", required = true) StdTeacher stdTeacher) {
    startPage();
    List<StdTeacher> list = stdTeacherService.selectStdTeacherList(stdTeacher);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：教师
   */
  @ApiOperation("初始化下拉数据：教师")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdTeacher> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdTeacher> lists = stdTeacherService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出教师列表
   */
  @ApiOperation("导出教师列表")
  @PreAuthorize(hasPermi = "standard:stdTeacher:export")
  @Log(title = "教师", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "教师", required = true) StdTeacher stdTeacher) throws IOException {
    List<StdTeacher> list = stdTeacherService.selectStdTeacherList(stdTeacher);
    ExcelUtil<StdTeacher> util = new ExcelUtil<StdTeacher>(StdTeacher.class);
     util.exportExcel(response,list, "stdTeacher");
  }

  /**
   * 获取教师详细信息
   */
  @ApiOperation("获取教师详细信息")
  @PreAuthorize(hasPermi = "standard:stdTeacher:query")
  @GetMapping(value = "/{teacherId}")
  public AjaxResult getInfo(
      @ApiParam(value = "教师Id", required = true) @PathVariable("teacherId") Long teacherId) {
    return AjaxResult.success(stdTeacherService.getById(teacherId));
  }

  /**
   * 新增教师
   */
  @ApiOperation("新增教师")
  @PreAuthorize(hasPermi = "standard:stdTeacher:add")
  @Log(title = "教师", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdTeacher stdTeacher) {
    return toAjax(stdTeacherService.saveTeacher(stdTeacher));
  }

  /**
   * 修改教师
   */
  @ApiOperation("修改教师")
  @PreAuthorize(hasPermi = "standard:stdTeacher:edit")
  @Log(title = "教师", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "教师", required = true) @RequestBody StdTeacher stdTeacher) {
    return toAjax(stdTeacherService.updateTeacherById(stdTeacher));
  }

  /**
   * 删除教师
   */
  @ApiOperation("删除教师")
  @PreAuthorize(hasPermi = "standard:stdTeacher:remove")
  @Log(title = "教师", businessType = BusinessType.DELETE)
  @DeleteMapping("/{teacherIds}")
  public AjaxResult remove(
      @ApiParam(value = "教师Ids", required = true) @PathVariable Long
          [] teacherIds) {
    return toAjax(stdTeacherService.removeByIds(Arrays.asList(teacherIds)));
  }
}
