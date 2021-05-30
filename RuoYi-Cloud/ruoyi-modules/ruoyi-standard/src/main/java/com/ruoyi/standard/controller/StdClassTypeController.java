package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdClassType;
import com.ruoyi.standard.service.IStdClassTypeService;
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
 * 班级类型Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "班级类型")
@RestController
@RequestMapping("/stdClassType")
public class StdClassTypeController extends BaseController {

  @Autowired
  private IStdClassTypeService stdClassTypeService;

  /**
   * 查询班级类型列表
   */
  @ApiOperation("查询班级类型列表")
  @PreAuthorize(hasPermi = "standard:stdClassType:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "班级类型", required = true) StdClassType stdClassType) {
    startPage();
    List<StdClassType> list = stdClassTypeService.selectStdClassTypeList(stdClassType);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：班级类型
   */
  @ApiOperation("初始化下拉数据：班级类型")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdClassType> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdClassType> lists = stdClassTypeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出班级类型列表
   */
  @ApiOperation("导出班级类型列表")
  @PreAuthorize(hasPermi = "standard:stdClassType:export")
  @Log(title = "班级类型", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "班级类型", required = true) StdClassType stdClassType) throws IOException {
    List<StdClassType> list = stdClassTypeService.selectStdClassTypeList(stdClassType);
    ExcelUtil<StdClassType> util = new ExcelUtil<StdClassType>(StdClassType.class);
     util.exportExcel(response,list, "stdClassType");
  }

  /**
   * 获取班级类型详细信息
   */
  @ApiOperation("获取班级类型详细信息")
  @PreAuthorize(hasPermi = "standard:stdClassType:query")
  @GetMapping(value = "/{classTypeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "班级类型Id", required = true) @PathVariable("classTypeId") Long classTypeId) {
    return AjaxResult.success(stdClassTypeService.getById(classTypeId));
  }

  /**
   * 新增班级类型
   */
  @ApiOperation("新增班级类型")
  @PreAuthorize(hasPermi = "standard:stdClassType:add")
  @Log(title = "班级类型", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdClassType stdClassType) {
    return toAjax(stdClassTypeService.save(stdClassType));
  }

  /**
   * 修改班级类型
   */
  @ApiOperation("修改班级类型")
  @PreAuthorize(hasPermi = "standard:stdClassType:edit")
  @Log(title = "班级类型", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "班级类型", required = true) @RequestBody StdClassType stdClassType) {
    return toAjax(stdClassTypeService.updateById(stdClassType));
  }

  /**
   * 删除班级类型
   */
  @ApiOperation("删除班级类型")
  @PreAuthorize(hasPermi = "standard:stdClassType:remove")
  @Log(title = "班级类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{classTypeIds}")
  public AjaxResult remove(
      @ApiParam(value = "班级类型Ids", required = true) @PathVariable Long
          [] classTypeIds) {
    return toAjax(stdClassTypeService.removeByIds(Arrays.asList(classTypeIds)));
  }
}
