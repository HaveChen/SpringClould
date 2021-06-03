package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltTestpaperDetail;
import com.ruoyi.collection.service.ICltTestpaperDetailService;
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
 * 试卷详情Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "试卷详情")
@RestController
@RequestMapping("/cltTestpaperDetail")
public class CltTestpaperDetailController extends BaseController {

  @Autowired
  private ICltTestpaperDetailService cltTestpaperDetailService;

  /**
   * 查询试卷详情列表
   */
  @ApiOperation("查询试卷详情列表")
  // @PreAuthorize(hasPermi = "collection:cltTestpaperDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "试卷详情", required = true) CltTestpaperDetail cltTestpaperDetail) {
    startPage();
    List<CltTestpaperDetail> list = cltTestpaperDetailService
        .selectCltTestpaperDetailList(cltTestpaperDetail);
    return getDataTable(list);
  }

  /**
   * 导出试卷详情列表
   */
  @ApiOperation("导出试卷详情列表")
  // @PreAuthorize(hasPermi = "collection:cltTestpaperDetail:export")
  @Log(title = "试卷详情", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "试卷详情", required = true) CltTestpaperDetail cltTestpaperDetail)
      throws IOException {
    List<CltTestpaperDetail> list = cltTestpaperDetailService
        .selectCltTestpaperDetailList(cltTestpaperDetail);
    ExcelUtil<CltTestpaperDetail> util = new ExcelUtil<>(
        CltTestpaperDetail.class);
     util.exportExcel(response,list, "cltTestpaperDetail");
  }

  /**
   * 获取试卷详情列表
   */
  @ApiOperation("获取根据试卷Id获取试卷详情列表")
  @GetMapping(value = "getTestpaperDetailList/{testpaperId}")
  public AjaxResult getTestpaperDetailList(
      @ApiParam(value = "试卷Id", required = true) @PathVariable("testpaperId") Long testpaperId) {
    return AjaxResult.success(cltTestpaperDetailService.getListByTestPaperId(testpaperId));
  }

  /**
   * 获取试卷详情详细信息
   */
  @ApiOperation("获取试卷详情详细信息")
  // @PreAuthorize(hasPermi = "collection:cltTestpaperDetail:query")
  @GetMapping(value = "/{testpaperDetailId}")
  public AjaxResult getInfo(
      @ApiParam(value = "试卷详情Id", required = true) @PathVariable("testpaperDetailId") Long testpaperDetailId) {
    return AjaxResult.success(cltTestpaperDetailService.getById(testpaperDetailId));
  }

  /**
   * 新增试卷详情
   */
  @ApiOperation("新增试卷详情")
  // @PreAuthorize(hasPermi = "collection:cltTestpaperDetail:add")
  @Log(title = "试卷详情", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltTestpaperDetail cltTestpaperDetail) {
    return toAjax(cltTestpaperDetailService.save(cltTestpaperDetail));
  }

  /**
   * 修改试卷详情
   */
  @ApiOperation("修改试卷详情")
  //@PreAuthorize(hasPermi = "collection:cltTestpaperDetail:edit")
  @Log(title = "试卷详情", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "试卷详情", required = true) @RequestBody CltTestpaperDetail cltTestpaperDetail) {
    return toAjax(cltTestpaperDetailService.updateById(cltTestpaperDetail));
  }

  /**
   * 删除试卷详情
   */
  @ApiOperation("删除试卷详情")
  // @PreAuthorize(hasPermi = "collection:cltTestpaperDetail:remove")
  @Log(title = "试卷详情", businessType = BusinessType.DELETE)
  @DeleteMapping("/{testpaperDetailIds}")
  public AjaxResult remove(
      @ApiParam(value = "试卷详情Ids", required = true) @PathVariable Long
          [] testpaperDetailIds) {
    return toAjax(cltTestpaperDetailService.removeByIds(Arrays.asList(testpaperDetailIds)));
  }
}
