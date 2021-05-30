package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdTeacherTitle;
import com.ruoyi.standard.service.IStdTeacherTitleService;
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
 * 教师职称Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "教师职称")
@RestController
@RequestMapping("/stdTeacherTitle")
public class StdTeacherTitleController extends BaseController {

  @Autowired
  private IStdTeacherTitleService stdTeacherTitleService;

  /**
   * 查询教师职称列表
   */
  @ApiOperation("查询教师职称列表")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "教师职称", required = true) StdTeacherTitle stdTeacherTitle) {
    startPage();
    List<StdTeacherTitle> list = stdTeacherTitleService.selectStdTeacherTitleList(stdTeacherTitle);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：教师职称
   */
  @ApiOperation("初始化下拉数据：教师职称")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdTeacherTitle> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdTeacherTitle> lists = stdTeacherTitleService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出教师职称列表
   */
  @ApiOperation("导出教师职称列表")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:export")
  @Log(title = "教师职称", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "教师职称", required = true) StdTeacherTitle stdTeacherTitle)
      throws IOException {
    List<StdTeacherTitle> list = stdTeacherTitleService.selectStdTeacherTitleList(stdTeacherTitle);
    ExcelUtil<StdTeacherTitle> util = new ExcelUtil<StdTeacherTitle>(StdTeacherTitle.class);
     util.exportExcel(response,list, "stdTeacherTitle");
  }

  /**
   * 获取教师职称详细信息
   */
  @ApiOperation("获取教师职称详细信息")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:query")
  @GetMapping(value = "/{teacherTitleId}")
  public AjaxResult getInfo(
      @ApiParam(value = "教师职称Id", required = true) @PathVariable("teacherTitleId") Long teacherTitleId) {
    return AjaxResult.success(stdTeacherTitleService.getById(teacherTitleId));
  }

  /**
   * 新增教师职称
   */
  @ApiOperation("新增教师职称")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:add")
  @Log(title = "教师职称", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdTeacherTitle stdTeacherTitle) {
    return toAjax(stdTeacherTitleService.save(stdTeacherTitle));
  }

  /**
   * 修改教师职称
   */
  @ApiOperation("修改教师职称")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:edit")
  @Log(title = "教师职称", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "教师职称", required = true) @RequestBody StdTeacherTitle stdTeacherTitle) {
    return toAjax(stdTeacherTitleService.updateById(stdTeacherTitle));
  }

  /**
   * 删除教师职称
   */
  @ApiOperation("删除教师职称")
  @PreAuthorize(hasPermi = "standard:stdTeacherTitle:remove")
  @Log(title = "教师职称", businessType = BusinessType.DELETE)
  @DeleteMapping("/{teacherTitleIds}")
  public AjaxResult remove(
      @ApiParam(value = "教师职称Ids", required = true) @PathVariable Long
          [] teacherTitleIds) {
    return toAjax(stdTeacherTitleService.removeByIds(Arrays.asList(teacherTitleIds)));
  }
}
