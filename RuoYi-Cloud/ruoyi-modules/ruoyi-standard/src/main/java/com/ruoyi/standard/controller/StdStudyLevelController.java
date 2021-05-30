package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdStudyLevel;
import com.ruoyi.standard.service.IStdStudyLevelService;
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
 * 学段Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "学段")
@RestController
@RequestMapping("/stdStudyLevel")
public class StdStudyLevelController extends BaseController {

  @Autowired
  private IStdStudyLevelService stdStudyLevelService;

  /**
   * 查询学段列表
   */
  @ApiOperation("查询学段列表")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "学段", required = true) StdStudyLevel stdStudyLevel) {
    startPage();
    List<StdStudyLevel> list = stdStudyLevelService.selectStdStudyLevelList(stdStudyLevel);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：学段
   */
  @ApiOperation("初始化下拉数据：学段")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdStudyLevel> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdStudyLevel> lists = stdStudyLevelService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出学段列表
   */
  @ApiOperation("导出学段列表")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:export")
  @Log(title = "学段", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "学段", required = true) StdStudyLevel stdStudyLevel) throws IOException {
    List<StdStudyLevel> list = stdStudyLevelService.selectStdStudyLevelList(stdStudyLevel);
    ExcelUtil<StdStudyLevel> util = new ExcelUtil<StdStudyLevel>(StdStudyLevel.class);
    util.exportExcel(response,list, "stdStudyLevel");
  }

  /**
   * 获取学段详细信息
   */
  @ApiOperation("获取学段详细信息")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:query")
  @GetMapping(value = "/{studyLevelId}")
  public AjaxResult getInfo(
      @ApiParam(value = "学段Id", required = true) @PathVariable("studyLevelId") Long studyLevelId) {
    return AjaxResult.success(stdStudyLevelService.getById(studyLevelId));
  }

  /**
   * 新增学段
   */
  @ApiOperation("新增学段")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:add")
  @Log(title = "学段", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdStudyLevel stdStudyLevel) {
    return toAjax(stdStudyLevelService.save(stdStudyLevel));
  }

  /**
   * 修改学段
   */
  @ApiOperation("修改学段")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:edit")
  @Log(title = "学段", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "学段", required = true) @RequestBody StdStudyLevel stdStudyLevel) {
    return toAjax(stdStudyLevelService.updateById(stdStudyLevel));
  }

  /**
   * 删除学段
   */
  @ApiOperation("删除学段")
  @PreAuthorize(hasPermi = "standard:stdStudyLevel:remove")
  @Log(title = "学段", businessType = BusinessType.DELETE)
  @DeleteMapping("/{studyLevelIds}")
  public AjaxResult remove(
      @ApiParam(value = "学段Ids", required = true) @PathVariable Long
          [] studyLevelIds) {
    return toAjax(stdStudyLevelService.removeByIds(Arrays.asList(studyLevelIds)));
  }
}
