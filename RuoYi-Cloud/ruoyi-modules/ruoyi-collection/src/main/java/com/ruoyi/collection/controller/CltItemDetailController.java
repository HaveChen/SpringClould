package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltItemDetail;
import com.ruoyi.collection.service.ICltItemDetailService;
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
 * 细目信息采集Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "细目信息采集")
@RestController
@RequestMapping("/cltItemDetail")
public class CltItemDetailController extends BaseController {

  @Autowired
  private ICltItemDetailService cltItemDetailService;

  /**
   * 查询细目信息采集列表
   */
  @ApiOperation("查询细目信息采集列表")
  // @PreAuthorize(hasPermi = "collection:cltItemDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "细目信息采集", required = true) CltItemDetail cltItemDetail) {
    startPage();
    List<CltItemDetail> list = cltItemDetailService.selectCltItemDetailList(cltItemDetail);
    return getDataTable(list);
  }

  /**
   * 导出细目信息采集列表
   */
  @ApiOperation("导出细目信息采集列表")
  //@PreAuthorize(hasPermi = "collection:cltItemDetail:export")
  @Log(title = "细目信息采集", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "细目信息采集", required = true) CltItemDetail cltItemDetail) throws IOException {
    List<CltItemDetail> list = cltItemDetailService.selectCltItemDetailList(cltItemDetail);
    ExcelUtil<CltItemDetail> util = new ExcelUtil<>(CltItemDetail.class);
    util.exportExcel(response,list, "cltItemDetail");
  }

  /**
   * 获取细目信息采集详细信息
   */
  @ApiOperation("获取细目信息采集详细信息")
  //@PreAuthorize(hasPermi = "collection:cltItemDetail:query")
  @GetMapping(value = "/{itemId}")
  public AjaxResult getInfo(
      @ApiParam(value = "细目信息采集Id", required = true) @PathVariable("itemId") String itemId) {
    return AjaxResult.success(cltItemDetailService.getById(itemId));
  }

  /**
   * 新增细目信息采集
   */
  @ApiOperation("新增细目信息采集")
  //@PreAuthorize(hasPermi = "collection:cltItemDetail:add")
  @Log(title = "细目信息采集", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltItemDetail cltItemDetail) {
    return toAjax(cltItemDetailService.save(cltItemDetail));
  }

  /**
   * 修改细目信息采集
   */
  @ApiOperation("修改细目信息采集")
  //@PreAuthorize(hasPermi = "collection:cltItemDetail:edit")
  @Log(title = "细目信息采集", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "细目信息采集", required = true) @RequestBody CltItemDetail cltItemDetail) {
    return toAjax(cltItemDetailService.updateById(cltItemDetail));
  }

  /**
   * 删除细目信息采集
   */
  @ApiOperation("删除细目信息采集")
  //@PreAuthorize(hasPermi = "collection:cltItemDetail:remove")
  @Log(title = "细目信息采集", businessType = BusinessType.DELETE)
  @DeleteMapping("/{itemIds}")
  public AjaxResult remove(
      @ApiParam(value = "细目信息采集Ids", required = true) @PathVariable String
          [] itemIds) {
    return toAjax(cltItemDetailService.removeByIds(Arrays.asList(itemIds)));
  }
}
