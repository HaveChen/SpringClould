package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.TenantInfo;
import com.ruoyi.system.service.ITenantInfoService;
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
 * 多租户Controller
 *
 * @author chenyou
 * @date 2021-06-01
 */
@Api(tags = "多租户")
@RestController
@RequestMapping("/tenant")
public class TenantInfoController extends BaseController {

  @Autowired
  private ITenantInfoService tenantInfoService;

  /**
   * 查询多租户列表
   */
  @ApiOperation("查询多租户列表")
  @PreAuthorize(hasPermi = "system:tenant:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "多租户", required = true) TenantInfo tenantInfo) {
    startPage();
    List<TenantInfo> list = tenantInfoService.selectTenantInfoList(tenantInfo);
    return getDataTable(list);
  }

  /**
   * 导出多租户列表
   */
  @Log(title = "多租户", businessType = BusinessType.EXPORT)
  @PostMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "多租户", required = true) TenantInfo tenantInfo)
      throws IOException {
    List<TenantInfo> list = tenantInfoService.selectTenantInfoList(tenantInfo);
    ExcelUtil<TenantInfo> util = new ExcelUtil<TenantInfo>(TenantInfo.class);
    util.exportExcel(response, list, "tenant");
  }

  /**
   * 获取多租户详细信息
   */
  @ApiOperation("获取多租户详细信息")
  @PreAuthorize(hasPermi = "system:tenant:query")
  @GetMapping(value = "/{tenantInfoId}")
  public AjaxResult getInfo(
      @ApiParam(value = "多租户Id", required = true) @PathVariable("tenantInfoId") Long tenantInfoId) {
    return AjaxResult.success(tenantInfoService.getById(tenantInfoId));
  }

  /**
   * 新增多租户
   */
  @ApiOperation("新增多租户")
  @PreAuthorize(hasPermi = "system:tenant:add")
  @Log(title = "多租户", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(
      @ApiParam(value = "多租户", required = true) @RequestBody TenantInfo tenantInfo) {
    return toAjax(tenantInfoService.save(tenantInfo));
  }

  /**
   * 修改多租户
   */
  @ApiOperation("修改多租户")
  @PreAuthorize(hasPermi = "system:tenant:edit")
  @Log(title = "多租户", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "多租户", required = true) @RequestBody TenantInfo tenantInfo) {
    return toAjax(tenantInfoService.updateById(tenantInfo));
  }

  /**
   * 删除多租户
   */
  @ApiOperation("删除多租户")
  @PreAuthorize(hasPermi = "system:tenant:remove")
  @Log(title = "多租户", businessType = BusinessType.DELETE)
  @DeleteMapping("/{tenantInfoIds}")
  public AjaxResult remove(
      @ApiParam(value = "多租户Ids", required = true) @PathVariable Long
          [] tenantInfoIds) {
    return toAjax(tenantInfoService.removeByIds(Arrays.asList(tenantInfoIds)));
  }
}
