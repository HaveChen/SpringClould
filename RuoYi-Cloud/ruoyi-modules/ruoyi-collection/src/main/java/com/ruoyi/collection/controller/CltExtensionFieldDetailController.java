package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltExtensionFieldDetail;
import com.ruoyi.collection.service.ICltExtensionFieldDetailService;
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
 * 采集扩展维度明细Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "采集扩展维度明细")
@RestController
@RequestMapping("/cltExtensionFieldDetail")
public class CltExtensionFieldDetailController extends BaseController {

  @Autowired
  private ICltExtensionFieldDetailService cltExtensionFieldDetailService;

  /**
   * 查询采集扩展维度明细列表
   */
  @ApiOperation("查询采集扩展维度明细列表")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "采集扩展维度明细", required = true) CltExtensionFieldDetail cltExtensionFieldDetail) {
    startPage();
    List<CltExtensionFieldDetail> list = cltExtensionFieldDetailService
        .selectCltExtensionFieldDetailList(cltExtensionFieldDetail);
    return getDataTable(list);
  }

  /**
   * 导出采集扩展维度明细列表
   */
  @ApiOperation("导出采集扩展维度明细列表")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:export")
  @Log(title = "采集扩展维度明细", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "采集扩展维度明细", required = true) CltExtensionFieldDetail cltExtensionFieldDetail)
      throws IOException {
    List<CltExtensionFieldDetail> list = cltExtensionFieldDetailService
        .selectCltExtensionFieldDetailList(cltExtensionFieldDetail);
    ExcelUtil<CltExtensionFieldDetail> util = new ExcelUtil<CltExtensionFieldDetail>(
        CltExtensionFieldDetail.class);
     util.exportExcel(response,list, "cltExtensionFieldDetail");
  }

  /**
   * 获取采集扩展维度明细详细信息
   */
  @ApiOperation("获取采集扩展维度明细详细信息")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:query")
  @GetMapping(value = "/{extensionDetailId}")
  public AjaxResult getInfo(
      @ApiParam(value = "采集扩展维度明细Id", required = true) @PathVariable("extensionDetailId") Long extensionDetailId) {
    return AjaxResult.success(cltExtensionFieldDetailService.getById(extensionDetailId));
  }

  /**
   * 新增采集扩展维度明细
   */
  @ApiOperation("新增采集扩展维度明细")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:add")
  @Log(title = "采集扩展维度明细", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExtensionFieldDetail cltExtensionFieldDetail) {
    return toAjax(cltExtensionFieldDetailService.save(cltExtensionFieldDetail));
  }

  /**
   * 修改采集扩展维度明细
   */
  @ApiOperation("修改采集扩展维度明细")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:edit")
  @Log(title = "采集扩展维度明细", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "采集扩展维度明细", required = true) @RequestBody CltExtensionFieldDetail cltExtensionFieldDetail) {
    return toAjax(cltExtensionFieldDetailService.updateById(cltExtensionFieldDetail));
  }

  /**
   * 删除采集扩展维度明细
   */
  @ApiOperation("删除采集扩展维度明细")
  @PreAuthorize(hasPermi = "collection:cltExtensionFieldDetail:remove")
  @Log(title = "采集扩展维度明细", businessType = BusinessType.DELETE)
  @DeleteMapping("/{extensionDetailIds}")
  public AjaxResult remove(
      @ApiParam(value = "采集扩展维度明细Ids", required = true) @PathVariable Long
          [] extensionDetailIds) {
    return toAjax(cltExtensionFieldDetailService.removeByIds(Arrays.asList(extensionDetailIds)));
  }
}
