package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.TenantInfoDetail;
import com.ruoyi.system.service.ITenantInfoDetailService;
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
 * 租户明细Controller
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Api(tags = "租户明细")
@RestController
@RequestMapping("/tenantDetail")
public class TenantInfoDetailController extends BaseController {

  @Autowired
  private ITenantInfoDetailService tenantInfoDetailService;

  /**
   * 查询租户明细列表
   */
  @ApiOperation("查询租户明细列表")
  @PreAuthorize(hasPermi = "system:tenantDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "租户明细", required = true) TenantInfoDetail tenantInfoDetail) {
    startPage();
    List<TenantInfoDetail> list = tenantInfoDetailService
        .selectTenantInfoDetailList(tenantInfoDetail);
    return getDataTable(list);
  }

  /**
   * 导出租户明细列表
   */
  @Log(title = "租户明细", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "租户明细", required = true) TenantInfoDetail tenantInfoDetail)
      throws IOException {
    List<TenantInfoDetail> list = tenantInfoDetailService
        .selectTenantInfoDetailList(tenantInfoDetail);
    ExcelUtil<TenantInfoDetail> util = new ExcelUtil<TenantInfoDetail>(TenantInfoDetail.class);
    util.exportExcel(response, list, "tenantDetail");
  }

  /**
   * 获取租户明细详细信息
   */
  @ApiOperation("获取租户明细详细信息")
  @PreAuthorize(hasPermi = "system:tenantDetail:query")
  @GetMapping(value = "/{tenantInfoDetailId}")
  public AjaxResult getInfo(
      @ApiParam(value = "租户明细Id", required = true) @PathVariable("tenantInfoDetailId") Long tenantInfoDetailId) {
    return AjaxResult.success(tenantInfoDetailService.getById(tenantInfoDetailId));
  }

  /**
   * 新增租户明细
   */
  @ApiOperation("新增租户明细")
  @PreAuthorize(hasPermi = "system:tenantDetail:add")
  @Log(title = "租户明细", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(
      @ApiParam(value = "租户明细", required = true) @RequestBody TenantInfoDetail tenantInfoDetail) {
    return toAjax(tenantInfoDetailService.save(tenantInfoDetail));
  }

  /**
   * 修改租户明细
   */
  @ApiOperation("修改租户明细")
  @PreAuthorize(hasPermi = "system:tenantDetail:edit")
  @Log(title = "租户明细", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "租户明细", required = true) @RequestBody TenantInfoDetail tenantInfoDetail) {
    return toAjax(tenantInfoDetailService.updateById(tenantInfoDetail));
  }

  /**
   * 删除租户明细
   */
  @ApiOperation("删除租户明细")
  @PreAuthorize(hasPermi = "system:tenantDetail:remove")
  @Log(title = "租户明细", businessType = BusinessType.DELETE)
  @DeleteMapping("/{tenantInfoDetailIds}")
  public AjaxResult remove(
      @ApiParam(value = "租户明细Ids", required = true) @PathVariable Long
          [] tenantInfoDetailIds) {
    return toAjax(tenantInfoDetailService.removeByIds(Arrays.asList(tenantInfoDetailIds)));
  }
}
