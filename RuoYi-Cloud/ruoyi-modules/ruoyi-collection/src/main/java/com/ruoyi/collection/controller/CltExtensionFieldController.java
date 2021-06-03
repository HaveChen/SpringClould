package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltExtensionField;
import com.ruoyi.collection.service.ICltExtensionFieldService;
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
 * 采集扩展维度Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "采集扩展维度")
@RestController
@RequestMapping("/cltExtensionField")
public class CltExtensionFieldController extends BaseController {

  @Autowired
  private ICltExtensionFieldService cltExtensionFieldService;

  /**
   * 查询采集扩展维度列表
   */
  @ApiOperation("查询采集扩展维度列表")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "采集扩展维度", required = true) CltExtensionField cltExtensionField) {
    startPage();
    List<CltExtensionField> list = cltExtensionFieldService
        .selectCltExtensionFieldList(cltExtensionField);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：集扩展维度
   */
  @ApiOperation("初始化下拉数据：集扩展维度")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltExtensionField> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltExtensionField> lists = cltExtensionFieldService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出采集扩展维度列表
   */
  @ApiOperation("导出采集扩展维度列表")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:export")
  @Log(title = "采集扩展维度", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "采集扩展维度", required = true) CltExtensionField cltExtensionField)
      throws IOException {
    List<CltExtensionField> list = cltExtensionFieldService
        .selectCltExtensionFieldList(cltExtensionField);
    ExcelUtil<CltExtensionField> util = new ExcelUtil<>(CltExtensionField.class);
     util.exportExcel(response,list, "cltExtensionField");
  }

  /**
   * 获取采集扩展维度详细信息
   */
  @ApiOperation("获取采集扩展维度详细信息")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:query")
  @GetMapping(value = "/{extensionId}")
  public AjaxResult getInfo(
      @ApiParam(value = "采集扩展维度Id", required = true) @PathVariable("extensionId") Long extensionId) {
    return AjaxResult.success(cltExtensionFieldService.getById(extensionId));
  }

  /**
   * 新增采集扩展维度
   */
  @ApiOperation("新增采集扩展维度")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:add")
  @Log(title = "采集扩展维度", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExtensionField cltExtensionField) {
    return toAjax(cltExtensionFieldService.save(cltExtensionField));
  }

  /**
   * 修改采集扩展维度
   */
  @ApiOperation("修改采集扩展维度")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:edit")
  @Log(title = "采集扩展维度", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "采集扩展维度", required = true) @RequestBody CltExtensionField cltExtensionField) {
    return toAjax(cltExtensionFieldService.updateById(cltExtensionField));
  }

  /**
   * 删除采集扩展维度
   */
  @ApiOperation("删除采集扩展维度")
  @PreAuthorize(hasPermi = "collection:cltExtensionField:remove")
  @Log(title = "采集扩展维度", businessType = BusinessType.DELETE)
  @DeleteMapping("/{extensionIds}")
  public AjaxResult remove(
      @ApiParam(value = "采集扩展维度Ids", required = true) @PathVariable Long
          [] extensionIds) {
    return toAjax(cltExtensionFieldService.removeByIds(Arrays.asList(extensionIds)));
  }
}
