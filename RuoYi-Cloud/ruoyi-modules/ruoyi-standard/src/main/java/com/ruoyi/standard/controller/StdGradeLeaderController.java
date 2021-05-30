package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdGradeLeader;
import com.ruoyi.standard.service.IStdGradeLeaderService;
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
 * 年级组长Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "年级组长")
@RestController
@RequestMapping("/stdGradeLeader")
public class StdGradeLeaderController extends BaseController {

  @Autowired
  private IStdGradeLeaderService stdGradeLeaderService;

  /**
   * 查询年级组长列表
   */
  @ApiOperation("查询年级组长列表")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "年级组长", required = true) StdGradeLeader stdGradeLeader) {
    startPage();
    List<StdGradeLeader> list = stdGradeLeaderService.selectStdGradeLeaderList(stdGradeLeader);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：年级组长
   */
  @ApiOperation("初始化下拉数据：年级组长")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdGradeLeader> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdGradeLeader> lists = stdGradeLeaderService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出年级组长列表
   */
  @ApiOperation("导出年级组长列表")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:export")
  @Log(title = "年级组长", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "年级组长", required = true) StdGradeLeader stdGradeLeader) throws IOException {
    List<StdGradeLeader> list = stdGradeLeaderService.selectStdGradeLeaderList(stdGradeLeader);
    ExcelUtil<StdGradeLeader> util = new ExcelUtil<StdGradeLeader>(StdGradeLeader.class);
     util.exportExcel(response,list, "stdGradeLeader");
  }

  /**
   * 获取年级组长详细信息
   */
  @ApiOperation("获取年级组长详细信息")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:query")
  @GetMapping(value = "/{gradeLeaderId}")
  public AjaxResult getInfo(
      @ApiParam(value = "年级组长Id", required = true) @PathVariable("gradeLeaderId") Long gradeLeaderId) {
    return AjaxResult.success(stdGradeLeaderService.getById(gradeLeaderId));
  }

  /**
   * 新增年级组长
   */
  @ApiOperation("新增年级组长")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:add")
  @Log(title = "年级组长", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdGradeLeader stdGradeLeader) {
    return toAjax(stdGradeLeaderService.saveGtadeLeader(stdGradeLeader));
  }

  /**
   * 修改年级组长
   */
  @ApiOperation("修改年级组长")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:edit")
  @Log(title = "年级组长", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "年级组长", required = true) @RequestBody StdGradeLeader stdGradeLeader) {
    return toAjax(stdGradeLeaderService.updateGradeLeaderById(stdGradeLeader));
  }

  /**
   * 删除年级组长
   */
  @ApiOperation("删除年级组长")
  @PreAuthorize(hasPermi = "standard:stdGradeLeader:remove")
  @Log(title = "年级组长", businessType = BusinessType.DELETE)
  @DeleteMapping("/{gradeLeaderIds}")
  public AjaxResult remove(
      @ApiParam(value = "年级组长Ids", required = true) @PathVariable Long
          [] gradeLeaderIds) {
    return toAjax(stdGradeLeaderService.removeByIds(Arrays.asList(gradeLeaderIds)));
  }
}
