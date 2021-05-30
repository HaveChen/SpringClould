package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdSubjectCombine;
import com.ruoyi.standard.service.IStdSubjectCombineService;
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
 * 科目组合类型Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "科目组合类型")
@RestController
@RequestMapping("/stdSubjectCombine")
public class StdSubjectCombineController extends BaseController {

  @Autowired
  private IStdSubjectCombineService stdSubjectCombineService;

  /**
   * 查询科目组合类型列表
   */
  @ApiOperation("查询科目组合类型列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "科目组合类型", required = true) StdSubjectCombine stdSubjectCombine) {
    startPage();
    List<StdSubjectCombine> list = stdSubjectCombineService
        .selectStdSubjectCombineList(stdSubjectCombine);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：科目组合类型
   */
  @ApiOperation("初始化下拉数据：科目组合类型")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdSubjectCombine> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdSubjectCombine> lists = stdSubjectCombineService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出科目组合类型列表
   */
  @ApiOperation("导出科目组合类型列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:export")
  @Log(title = "科目组合类型", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "科目组合类型", required = true) StdSubjectCombine stdSubjectCombine)
      throws IOException {
    List<StdSubjectCombine> list = stdSubjectCombineService
        .selectStdSubjectCombineList(stdSubjectCombine);
    ExcelUtil<StdSubjectCombine> util = new ExcelUtil<>(StdSubjectCombine.class);
     util.exportExcel(response,list, "stdSubjectCombine");
  }

  /**
   * 获取科目组合类型详细信息
   */
  @ApiOperation("获取科目组合类型详细信息")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:query")
  @GetMapping(value = "/{subjectCombineId}")
  public AjaxResult getInfo(
      @ApiParam(value = "科目组合类型Id", required = true) @PathVariable("subjectCombineId") Long subjectCombineId) {
    return AjaxResult.success(stdSubjectCombineService.getById(subjectCombineId));
  }

  /**
   * 新增科目组合类型
   */
  @ApiOperation("新增科目组合类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:add")
  @Log(title = "科目组合类型", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdSubjectCombine stdSubjectCombine) {
    return toAjax(stdSubjectCombineService.save(stdSubjectCombine));
  }

  /**
   * 修改科目组合类型
   */
  @ApiOperation("修改科目组合类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:edit")
  @Log(title = "科目组合类型", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "科目组合类型", required = true) @RequestBody StdSubjectCombine stdSubjectCombine) {
    return toAjax(stdSubjectCombineService.updateById(stdSubjectCombine));
  }

  /**
   * 删除科目组合类型
   */
  @ApiOperation("删除科目组合类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectCombine:remove")
  @Log(title = "科目组合类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{subjectCombineIds}")
  public AjaxResult remove(
      @ApiParam(value = "科目组合类型Ids", required = true) @PathVariable Long
          [] subjectCombineIds) {
    return toAjax(stdSubjectCombineService.removeByIds(Arrays.asList(subjectCombineIds)));
  }
}
