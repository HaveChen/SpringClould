package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdTeachClass;
import com.ruoyi.standard.service.IStdTeachClassService;
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
 * 教学班Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "教学班")
@RestController
@RequestMapping("/stdTeachClass")
public class StdTeachClassController extends BaseController {

  @Autowired
  private IStdTeachClassService stdTeachClassService;

  /**
   * 查询教学班列表
   */
  @ApiOperation("查询教学班列表")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "教学班", required = true) StdTeachClass stdTeachClass) {
    startPage();
    List<StdTeachClass> list = stdTeachClassService.selectStdTeachClassList(stdTeachClass);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：教学班
   */
  @ApiOperation("初始化下拉数据：教学班")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdTeachClass> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdTeachClass> lists = stdTeachClassService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出教学班列表
   */
  @ApiOperation("导出教学班列表")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:export")
  @Log(title = "教学班", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "教学班", required = true) StdTeachClass stdTeachClass) throws IOException {
    List<StdTeachClass> list = stdTeachClassService.selectStdTeachClassList(stdTeachClass);
    ExcelUtil<StdTeachClass> util = new ExcelUtil<StdTeachClass>(StdTeachClass.class);
     util.exportExcel(response,list, "stdTeachClass");
  }

  /**
   * 获取教学班详细信息
   */
  @ApiOperation("获取教学班详细信息")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:query")
  @GetMapping(value = "/{teachClassId}")
  public AjaxResult getInfo(
      @ApiParam(value = "教学班Id", required = true) @PathVariable("teachClassId") Long teachClassId) {
    return AjaxResult.success(stdTeachClassService.getById(teachClassId));
  }

  /**
   * 新增教学班
   */
  @ApiOperation("新增教学班")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:add")
  @Log(title = "教学班", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdTeachClass stdTeachClass) {
    return toAjax(stdTeachClassService.saveTeachClass(stdTeachClass));
  }

  /**
   * 修改教学班
   */
  @ApiOperation("修改教学班")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:edit")
  @Log(title = "教学班", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "教学班", required = true) @RequestBody StdTeachClass stdTeachClass) {
    return toAjax(stdTeachClassService.updateTeachClassById(stdTeachClass));
  }

  /**
   * 删除教学班
   */
  @ApiOperation("删除教学班")
  @PreAuthorize(hasPermi = "standard:stdTeachClass:remove")
  @Log(title = "教学班", businessType = BusinessType.DELETE)
  @DeleteMapping("/{teachClassIds}")
  public AjaxResult remove(
      @ApiParam(value = "教学班Ids", required = true) @PathVariable Long
          [] teachClassIds) {
    return toAjax(stdTeachClassService.removeByIds(Arrays.asList(teachClassIds)));
  }
}
