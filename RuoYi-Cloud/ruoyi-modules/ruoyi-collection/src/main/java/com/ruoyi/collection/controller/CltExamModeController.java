package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltExamMode;
import com.ruoyi.collection.service.ICltExamModeService;
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
 * 考试模式Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "考试模式")
@RestController
@RequestMapping("/cltExamMode")
public class CltExamModeController extends BaseController {

  @Autowired
  private ICltExamModeService cltExamModeService;

  /**
   * 查询考试模式列表
   */
  @ApiOperation("查询考试模式列表")
  @PreAuthorize(hasPermi = "collection:cltExamMode:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "考试模式", required = true) CltExamMode cltExamMode) {
    startPage();
    List<CltExamMode> list = cltExamModeService.selectCltExamModeList(cltExamMode);
    return getDataTable(list);
  }

  /**
   * 导出考试模式列表
   */
  @ApiOperation("导出考试模式列表")
  @PreAuthorize(hasPermi = "collection:cltExamMode:export")
  @Log(title = "考试模式", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "考试模式", required = true) CltExamMode cltExamMode) throws IOException {
    List<CltExamMode> list = cltExamModeService.selectCltExamModeList(cltExamMode);
    ExcelUtil<CltExamMode> util = new ExcelUtil<>(CltExamMode.class);
     util.exportExcel(response,list, "cltExamMode");
  }

  /**
   * 查询有效下拉数据：考试模式
   */
  @ApiOperation("初始化下拉数据：考试模式")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltExamMode> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltExamMode> lists = cltExamModeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 获取考试模式详细信息
   */
  @ApiOperation("获取考试模式详细信息")
  @PreAuthorize(hasPermi = "collection:cltExamMode:query")
  @GetMapping(value = "/{examModeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "考试模式Id", required = true) @PathVariable("examModeId") Long examModeId) {
    return AjaxResult.success(cltExamModeService.getById(examModeId));
  }

  /**
   * 新增考试模式
   */
  @ApiOperation("新增考试模式")
  @PreAuthorize(hasPermi = "collection:cltExamMode:add")
  @Log(title = "考试模式", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExamMode cltExamMode) {
    return toAjax(cltExamModeService.save(cltExamMode));
  }

  /**
   * 修改考试模式
   */
  @ApiOperation("修改考试模式")
  @PreAuthorize(hasPermi = "collection:cltExamMode:edit")
  @Log(title = "考试模式", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "考试模式", required = true) @RequestBody CltExamMode cltExamMode) {
    return toAjax(cltExamModeService.updateById(cltExamMode));
  }

  /**
   * 删除考试模式
   */
  @ApiOperation("删除考试模式")
  @PreAuthorize(hasPermi = "collection:cltExamMode:remove")
  @Log(title = "考试模式", businessType = BusinessType.DELETE)
  @DeleteMapping("/{examModeIds}")
  public AjaxResult remove(
      @ApiParam(value = "考试模式Ids", required = true) @PathVariable Long
          [] examModeIds) {
    return toAjax(cltExamModeService.removeByIds(Arrays.asList(examModeIds)));
  }
}
