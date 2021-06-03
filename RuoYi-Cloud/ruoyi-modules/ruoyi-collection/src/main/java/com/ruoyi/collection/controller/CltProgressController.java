package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltProgressService;
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
 * 采集进度Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "采集进度")
@RestController
@RequestMapping("/cltProgress")
public class CltProgressController extends BaseController {

  @Autowired
  private ICltProgressService cltProgressService;

  /**
   * 查询采集进度列表
   */
  @ApiOperation("查询采集进度列表")
  //@PreAuthorize(hasPermi = "collection:cltProgress:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "采集进度", required = true) CltProgress cltProgress) {
    startPage();
    List<CltProgress> list = cltProgressService.selectCltProgressList(cltProgress);
    return getDataTable(list);
  }

  /**
   * 导出采集进度列表
   */
  @ApiOperation("导出采集进度列表")
  //@PreAuthorize(hasPermi = "collection:cltProgress:export")
  @Log(title = "采集进度", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "采集进度", required = true) CltProgress cltProgress) throws IOException {
    List<CltProgress> list = cltProgressService.selectCltProgressList(cltProgress);
    ExcelUtil<CltProgress> util = new ExcelUtil<>(CltProgress.class);
     util.exportExcel(response,list, "cltProgress");
  }

  /**
   * 获取采集进度详细信息
   */
  @ApiOperation("获取采集进度详细信息")
  //@PreAuthorize(hasPermi = "collection:cltProgress:query")
  @GetMapping(value = "/{progressId}")
  public AjaxResult getInfo(
      @ApiParam(value = "采集进度Id", required = true) @PathVariable("progressId") Long progressId) {
    return AjaxResult.success(cltProgressService.getById(progressId));
  }

  /**
   * 新增采集进度
   */
  @ApiOperation("新增采集进度")
  // @PreAuthorize(hasPermi = "collection:cltProgress:add")
  @Log(title = "采集进度", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltProgress cltProgress) {
    return toAjax(cltProgressService.save(cltProgress));
  }

  /**
   * 修改采集进度
   */
  @ApiOperation("修改采集进度")
  //@PreAuthorize(hasPermi = "collection:cltProgress:edit")
  @Log(title = "采集进度", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "采集进度", required = true) @RequestBody CltProgress cltProgress) {
    return toAjax(cltProgressService.updateById(cltProgress));
  }

  /**
   * 删除采集进度
   */
  @ApiOperation("删除采集进度")
  //@PreAuthorize(hasPermi = "collection:cltProgress:remove")
  @Log(title = "采集进度", businessType = BusinessType.DELETE)
  @DeleteMapping("/{progressIds}")
  public AjaxResult remove(
      @ApiParam(value = "采集进度Ids", required = true) @PathVariable Long
          [] progressIds) {
    return toAjax(cltProgressService.removeByIds(Arrays.asList(progressIds)));
  }
}
