package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.api.domain.StdSubjectClassify;
import com.ruoyi.standard.service.IStdSubjectClassifyService;
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
 * 科目分类Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "科目分类")
@RestController
@RequestMapping("/stdSubjectClassify")
public class StdSubjectClassifyController extends BaseController {

  @Autowired
  private IStdSubjectClassifyService stdSubjectClassifyService;

  /**
   * 查询科目分类列表
   */
  @ApiOperation("查询科目分类列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "科目分类", required = true) StdSubjectClassify stdSubjectClassify) {
    startPage();
    List<StdSubjectClassify> list = stdSubjectClassifyService
        .selectStdSubjectClassifyList(stdSubjectClassify);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：科目分类
   */
  @ApiOperation("初始化下拉数据：科目分类")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdSubjectClassify> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdSubjectClassify> lists = stdSubjectClassifyService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出科目分类列表
   */
  @ApiOperation("导出科目分类列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:export")
  @Log(title = "科目分类", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "科目分类", required = true) StdSubjectClassify stdSubjectClassify)
      throws IOException {
    List<StdSubjectClassify> list = stdSubjectClassifyService
        .selectStdSubjectClassifyList(stdSubjectClassify);
    ExcelUtil<StdSubjectClassify> util = new ExcelUtil<StdSubjectClassify>(
        StdSubjectClassify.class);
     util.exportExcel(response,list, "stdSubjectClassify");
  }

  /**
   * 获取科目分类详细信息
   */
  @ApiOperation("获取科目分类详细信息")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:query")
  @GetMapping(value = "/{subjectClassifyId}")
  public AjaxResult getInfo(
      @ApiParam(value = "科目分类Id", required = true) @PathVariable("subjectClassifyId") Long subjectClassifyId) {
    return AjaxResult.success(stdSubjectClassifyService.getById(subjectClassifyId));
  }

  /**
   * 新增科目分类
   */
  @ApiOperation("新增科目分类")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:add")
  @Log(title = "科目分类", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdSubjectClassify stdSubjectClassify) {
    return toAjax(stdSubjectClassifyService.save(stdSubjectClassify));
  }

  /**
   * 修改科目分类
   */
  @ApiOperation("修改科目分类")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:edit")
  @Log(title = "科目分类", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "科目分类", required = true) @RequestBody StdSubjectClassify stdSubjectClassify) {
    return toAjax(stdSubjectClassifyService.updateById(stdSubjectClassify));
  }

  /**
   * 删除科目分类
   */
  @ApiOperation("删除科目分类")
  @PreAuthorize(hasPermi = "standard:stdSubjectClassify:remove")
  @Log(title = "科目分类", businessType = BusinessType.DELETE)
  @DeleteMapping("/{subjectClassifyIds}")
  public AjaxResult remove(
      @ApiParam(value = "科目分类Ids", required = true) @PathVariable Long
          [] subjectClassifyIds) {
    return toAjax(stdSubjectClassifyService.removeByIds(Arrays.asList(subjectClassifyIds)));
  }
}
