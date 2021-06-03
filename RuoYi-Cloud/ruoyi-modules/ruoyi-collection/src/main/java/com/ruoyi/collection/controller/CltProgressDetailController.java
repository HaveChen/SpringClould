package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltProgressDetail;
import com.ruoyi.collection.service.ICltProgressDetailService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
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
 * 采集错误明细Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "采集错误明细")
@RestController
@RequestMapping("/cltProgressDetail")
public class CltProgressDetailController extends BaseController {

  @Autowired
  private ICltProgressDetailService cltProgressDetailService;

  /**
   * 查询采集错误明细列表
   */
  @ApiOperation("查询采集错误明细列表")
  //@PreAuthorize(hasPermi = "collection:cltProgressDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "采集错误明细", required = true) CltProgressDetail cltProgressDetail) {
    startPage();
    List<CltProgressDetail> list = cltProgressDetailService
        .selectCltProgressDetailList(cltProgressDetail);
    return getDataTable(list);
  }

  /**
   * 导出采集错误明细列表
   */
  @ApiOperation("导出采集错误明细列表")
  // @PreAuthorize(hasPermi = "collection:cltProgressDetail:export")
  @Log(title = "采集错误明细", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "采集错误明细", required = true) CltProgressDetail cltProgressDetail)
      throws IOException {
    List<CltProgressDetail> list = cltProgressDetailService
        .selectCltProgressDetailList(cltProgressDetail);
    ExcelUtil<CltProgressDetail> util = new ExcelUtil<>(CltProgressDetail.class);
     util.exportExcel(response,list, "cltProgressDetail");
  }

  /**
   * 获取采集错误明细详细信息
   */
  @ApiOperation("获取采集错误明细详细信息")
  // @PreAuthorize(hasPermi = "collection:cltProgressDetail:query")
  @GetMapping(value = "/{progressDetailId}")
  public AjaxResult getInfo(
      @ApiParam(value = "采集错误明细Id", required = true) @PathVariable("progressDetailId") Long progressDetailId) {
    return AjaxResult.success(cltProgressDetailService.getById(progressDetailId));
  }

  /**
   * 新增采集错误明细
   */
  @ApiOperation("新增采集错误明细")
  //@PreAuthorize(hasPermi = "collection:cltProgressDetail:add")
  @Log(title = "采集错误明细", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltProgressDetail cltProgressDetail) {
    return toAjax(cltProgressDetailService.save(cltProgressDetail));
  }

  /**
   * 修改采集错误明细
   */
  @ApiOperation("修改采集错误明细")
  //@PreAuthorize(hasPermi = "collection:cltProgressDetail:edit")
  @Log(title = "采集错误明细", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "采集错误明细", required = true) @RequestBody CltProgressDetail cltProgressDetail) {
    return toAjax(cltProgressDetailService.updateById(cltProgressDetail));
  }

  /**
   * 删除采集错误明细
   */
  @ApiOperation("删除采集错误明细")
  //@PreAuthorize(hasPermi = "collection:cltProgressDetail:remove")
  @Log(title = "采集错误明细", businessType = BusinessType.DELETE)
  @DeleteMapping("/{progressDetailIds}")
  public AjaxResult remove(
      @ApiParam(value = "采集错误明细Ids", required = true) @PathVariable Long
          [] progressDetailIds) {
    return toAjax(cltProgressDetailService.removeByIds(Arrays.asList(progressDetailIds)));
  }
}
