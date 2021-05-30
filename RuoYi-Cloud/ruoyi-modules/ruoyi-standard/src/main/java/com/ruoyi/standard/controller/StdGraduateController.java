package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdGraduate;
import com.ruoyi.standard.service.IStdGraduateService;
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
 * 届别Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "届别")
@RestController
@RequestMapping("/stdGraduate")
public class StdGraduateController extends BaseController {

  @Autowired
  private IStdGraduateService stdGraduateService;

  /**
   * 查询届别列表
   */
  @ApiOperation("查询届别列表")
  @PreAuthorize(hasPermi = "standard:stdGraduate:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "届别", required = true) StdGraduate stdGraduate) {
    startPage();
    List<StdGraduate> list = stdGraduateService.selectStdGraduateList(stdGraduate);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：届别
   */
  @ApiOperation("初始化下拉数据：届别")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdGraduate> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdGraduate> lists = stdGraduateService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出届别列表
   */
  @ApiOperation("导出届别列表")
  @PreAuthorize(hasPermi = "standard:stdGraduate:export")
  @Log(title = "届别", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "届别", required = true) StdGraduate stdGraduate) throws IOException {
    List<StdGraduate> list = stdGraduateService.selectStdGraduateList(stdGraduate);
    ExcelUtil<StdGraduate> util = new ExcelUtil<StdGraduate>(StdGraduate.class);
     util.exportExcel(response,list, "stdGraduate");
  }

  /**
   * 获取届别详细信息
   */
  @ApiOperation("获取届别详细信息")
  @PreAuthorize(hasPermi = "standard:stdGraduate:query")
  @GetMapping(value = "/{graduateId}")
  public AjaxResult getInfo(
      @ApiParam(value = "届别Id", required = true) @PathVariable("graduateId") Long graduateId) {
    return AjaxResult.success(stdGraduateService.getById(graduateId));
  }

  /**
   * 新增届别
   */
  @ApiOperation("新增届别")
  @PreAuthorize(hasPermi = "standard:stdGraduate:add")
  @Log(title = "届别", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdGraduate stdGraduate) {
    return toAjax(stdGraduateService.save(stdGraduate));
  }

  /**
   * 修改届别
   */
  @ApiOperation("修改届别")
  @PreAuthorize(hasPermi = "standard:stdGraduate:edit")
  @Log(title = "届别", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "届别", required = true) @RequestBody StdGraduate stdGraduate) {
    return toAjax(stdGraduateService.updateById(stdGraduate));
  }

  /**
   * 删除届别
   */
  @ApiOperation("删除届别")
  @PreAuthorize(hasPermi = "standard:stdGraduate:remove")
  @Log(title = "届别", businessType = BusinessType.DELETE)
  @DeleteMapping("/{graduateIds}")
  public AjaxResult remove(
      @ApiParam(value = "届别Ids", required = true) @PathVariable Long
          [] graduateIds) {
    return toAjax(stdGraduateService.removeByIds(Arrays.asList(graduateIds)));
  }
}
