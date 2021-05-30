package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdTerm;
import com.ruoyi.standard.service.IStdTermService;
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
 * 学期Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "学期")
@RestController
@RequestMapping("/stdTerm")
public class StdTermController extends BaseController {

  @Autowired
  private IStdTermService stdTermService;

  /**
   * 查询学期列表
   */
  @ApiOperation("查询学期列表")
  @PreAuthorize(hasPermi = "standard:stdTerm:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "学期", required = true) StdTerm stdTerm) {
    startPage();
    List<StdTerm> list = stdTermService.selectStdTermList(stdTerm);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：学期
   */
  @ApiOperation("初始化下拉数据：学期")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdTerm> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdTerm> lists = stdTermService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出学期列表
   */
  @ApiOperation("导出学期列表")
  @PreAuthorize(hasPermi = "standard:stdTerm:export")
  @Log(title = "学期", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "学期", required = true) StdTerm stdTerm) throws IOException {
    List<StdTerm> list = stdTermService.selectStdTermList(stdTerm);
    ExcelUtil<StdTerm> util = new ExcelUtil<StdTerm>(StdTerm.class);
     util.exportExcel(response,list, "stdTerm");
  }

  /**
   * 获取学期详细信息
   */
  @ApiOperation("获取学期详细信息")
  @PreAuthorize(hasPermi = "standard:stdTerm:query")
  @GetMapping(value = "/{termId}")
  public AjaxResult getInfo(
      @ApiParam(value = "学期Id", required = true) @PathVariable("termId") Long termId) {
    return AjaxResult.success(stdTermService.getById(termId));
  }

  /**
   * 新增学期
   */
  @ApiOperation("新增学期")
  @PreAuthorize(hasPermi = "standard:stdTerm:add")
  @Log(title = "学期", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdTerm stdTerm) {
    return toAjax(stdTermService.save(stdTerm));
  }

  /**
   * 修改学期
   */
  @ApiOperation("修改学期")
  @PreAuthorize(hasPermi = "standard:stdTerm:edit")
  @Log(title = "学期", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "学期", required = true) @RequestBody StdTerm stdTerm) {
    return toAjax(stdTermService.updateById(stdTerm));
  }

  /**
   * 删除学期
   */
  @ApiOperation("删除学期")
  @PreAuthorize(hasPermi = "standard:stdTerm:remove")
  @Log(title = "学期", businessType = BusinessType.DELETE)
  @DeleteMapping("/{termIds}")
  public AjaxResult remove(
      @ApiParam(value = "学期Ids", required = true) @PathVariable Long
          [] termIds) {
    return toAjax(stdTermService.removeByIds(Arrays.asList(termIds)));
  }
}
