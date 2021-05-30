package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.domain.StdSubject;
import com.ruoyi.standard.service.IStdSubjectService;
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
 * 科目管理Controller
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "科目管理")
@RestController
@RequestMapping("/stdSubject")
public class StdSubjectController extends BaseController {

  @Autowired
  private IStdSubjectService stdSubjectService;

  /**
   * 查询科目管理列表
   */
  @ApiOperation("查询科目管理列表")
  @PreAuthorize(hasPermi = "standard:stdSubject:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "科目管理", required = true) StdSubject stdSubject) {
    startPage();
    List<StdSubject> list = stdSubjectService.selectStdSubjectList(stdSubject);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：科目管理
   */
  @ApiOperation("初始化下拉数据：科目")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdSubject> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdSubject> lists = stdSubjectService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出科目管理列表
   */
  @ApiOperation("导出科目管理列表")
  @PreAuthorize(hasPermi = "standard:stdSubject:export")
  @Log(title = "科目管理", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "科目管理", required = true) StdSubject stdSubject) throws IOException {
    List<StdSubject> list = stdSubjectService.selectStdSubjectList(stdSubject);
    ExcelUtil<StdSubject> util = new ExcelUtil<StdSubject>(StdSubject.class);
     util.exportExcel(response,list, "stdSubject");
  }

  /**
   * 获取科目管理详细信息
   */
  @ApiOperation("获取科目管理详细信息")
  @PreAuthorize(hasPermi = "standard:stdSubject:query")
  @GetMapping(value = "/{subjectId}")
  public AjaxResult getInfo(
      @ApiParam(value = "科目管理Id", required = true) @PathVariable("subjectId") Long subjectId) {
    return AjaxResult.success(stdSubjectService.getById(subjectId));
  }

  /**
   * 新增科目管理
   */
  @ApiOperation("新增科目管理")
  @PreAuthorize(hasPermi = "standard:stdSubject:add")
  @Log(title = "科目管理", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdSubject stdSubject) {
    return toAjax(stdSubjectService.save(stdSubject));
  }

  /**
   * 修改科目管理
   */
  @ApiOperation("修改科目管理")
  @PreAuthorize(hasPermi = "standard:stdSubject:edit")
  @Log(title = "科目管理", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "科目管理", required = true) @RequestBody StdSubject stdSubject) {
    return toAjax(stdSubjectService.updateById(stdSubject));
  }

  /**
   * 删除科目管理
   */
  @ApiOperation("删除科目管理")
  @PreAuthorize(hasPermi = "standard:stdSubject:remove")
  @Log(title = "科目管理", businessType = BusinessType.DELETE)
  @DeleteMapping("/{subjectIds}")
  public AjaxResult remove(
      @ApiParam(value = "科目管理Ids", required = true) @PathVariable Long
          [] subjectIds) {
    return toAjax(stdSubjectService.removeByIds(Arrays.asList(subjectIds)));
  }
}
