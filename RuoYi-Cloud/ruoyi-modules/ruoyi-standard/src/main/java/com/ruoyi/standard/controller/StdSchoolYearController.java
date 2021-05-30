package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdSchoolYear;
import com.ruoyi.standard.service.IStdSchoolYearService;
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
 * 学年Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "学年")
@RestController
@RequestMapping("/stdSchoolYear")
public class StdSchoolYearController extends BaseController {

  @Autowired
  private IStdSchoolYearService stdSchoolYearService;

  /**
   * 查询学年列表
   */
  @ApiOperation("查询学年列表")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "学年", required = true) StdSchoolYear stdSchoolYear) {
    startPage();
    List<StdSchoolYear> list = stdSchoolYearService.selectStdSchoolYearList(stdSchoolYear);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：学年
   */
  @ApiOperation("初始化下拉数据：学年")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdSchoolYear> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdSchoolYear> lists = stdSchoolYearService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出学年列表
   */
  @ApiOperation("导出学年列表")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:export")
  @Log(title = "学年", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "学年", required = true) StdSchoolYear stdSchoolYear) throws IOException {
    List<StdSchoolYear> list = stdSchoolYearService.selectStdSchoolYearList(stdSchoolYear);
    ExcelUtil<StdSchoolYear> util = new ExcelUtil<StdSchoolYear>(StdSchoolYear.class);
     util.exportExcel(response,list, "stdSchoolYear");
  }

  /**
   * 获取学年详细信息
   */
  @ApiOperation("获取学年详细信息")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:query")
  @GetMapping(value = "/{schoolYearId}")
  public AjaxResult getInfo(
      @ApiParam(value = "学年Id", required = true) @PathVariable("schoolYearId") Long schoolYearId) {
    return AjaxResult.success(stdSchoolYearService.getById(schoolYearId));
  }

  /**
   * 新增学年
   */
  @ApiOperation("新增学年")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:add")
  @Log(title = "学年", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdSchoolYear stdSchoolYear) {
    return toAjax(stdSchoolYearService.save(stdSchoolYear));
  }

  /**
   * 修改学年
   */
  @ApiOperation("修改学年")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:edit")
  @Log(title = "学年", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "学年", required = true) @RequestBody StdSchoolYear stdSchoolYear) {
    return toAjax(stdSchoolYearService.updateById(stdSchoolYear));
  }

  /**
   * 删除学年
   */
  @ApiOperation("删除学年")
  @PreAuthorize(hasPermi = "standard:stdSchoolYear:remove")
  @Log(title = "学年", businessType = BusinessType.DELETE)
  @DeleteMapping("/{schoolYearIds}")
  public AjaxResult remove(
      @ApiParam(value = "学年Ids", required = true) @PathVariable Long
          [] schoolYearIds) {
    return toAjax(stdSchoolYearService.removeByIds(Arrays.asList(schoolYearIds)));
  }
}
