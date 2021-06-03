package com.ruoyi.standard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.standard.api.domain.StdSubjectType;
import com.ruoyi.standard.service.IStdSubjectTypeService;
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
 * 科目类型Controller
 *
 * @author chenyou
 * @date 2020-12-18
 */
@Api(tags = "科目类型")
@RestController
@RequestMapping("/stdSubjectType")
public class StdSubjectTypeController extends BaseController {

  @Autowired
  private IStdSubjectTypeService stdSubjectTypeService;

  /**
   * 查询科目类型列表
   */
  @ApiOperation("查询科目类型列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "科目类型", required = true) StdSubjectType stdSubjectType) {
    startPage();
    List<StdSubjectType> list = stdSubjectTypeService.selectStdSubjectTypeList(stdSubjectType);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：科目类型
   */
  @ApiOperation("初始化下拉数据：科目类型")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<StdSubjectType> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<StdSubjectType> lists = stdSubjectTypeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出科目类型列表
   */
  @ApiOperation("导出科目类型列表")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:export")
  @Log(title = "科目类型", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "科目类型", required = true) StdSubjectType stdSubjectType) throws IOException {
    List<StdSubjectType> list = stdSubjectTypeService.selectStdSubjectTypeList(stdSubjectType);
    ExcelUtil<StdSubjectType> util = new ExcelUtil<StdSubjectType>(StdSubjectType.class);
     util.exportExcel(response,list, "stdSubjectType");
  }

  /**
   * 获取科目类型详细信息
   */
  @ApiOperation("获取科目类型详细信息")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:query")
  @GetMapping(value = "/{subjectTypeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "科目类型Id", required = true) @PathVariable("subjectTypeId") Long subjectTypeId) {
    return AjaxResult.success(stdSubjectTypeService.getById(subjectTypeId));
  }

  /**
   * 新增科目类型
   */
  @ApiOperation("新增科目类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:add")
  @Log(title = "科目类型", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody StdSubjectType stdSubjectType) {
    return toAjax(stdSubjectTypeService.save(stdSubjectType));
  }

  /**
   * 修改科目类型
   */
  @ApiOperation("修改科目类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:edit")
  @Log(title = "科目类型", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "科目类型", required = true) @RequestBody StdSubjectType stdSubjectType) {
    return toAjax(stdSubjectTypeService.updateById(stdSubjectType));
  }

  /**
   * 删除科目类型
   */
  @ApiOperation("删除科目类型")
  @PreAuthorize(hasPermi = "standard:stdSubjectType:remove")
  @Log(title = "科目类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{subjectTypeIds}")
  public AjaxResult remove(
      @ApiParam(value = "科目类型Ids", required = true) @PathVariable Long
          [] subjectTypeIds) {
    return toAjax(stdSubjectTypeService.removeByIds(Arrays.asList(subjectTypeIds)));
  }
}
