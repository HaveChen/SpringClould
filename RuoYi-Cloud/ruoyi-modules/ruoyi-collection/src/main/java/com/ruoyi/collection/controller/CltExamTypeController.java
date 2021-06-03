package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltExamType;
import com.ruoyi.collection.service.ICltExamTypeService;
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
 * 考试类型Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "考试类型")
@RestController
@RequestMapping("/cltExamType")
public class CltExamTypeController extends BaseController {
  @Autowired
  private ICltExamTypeService cltExamTypeService;

  /**
   * 查询考试类型列表
   */
  @ApiOperation("查询考试类型列表")
  @PreAuthorize(hasPermi = "collection:cltExamType:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "考试类型", required = true) CltExamType cltExamType) {
    startPage();
    List<CltExamType> list = cltExamTypeService.selectCltExamTypeList(cltExamType);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：考试类型
   */
  @ApiOperation("初始化下拉数据：考试类型")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltExamType> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltExamType> lists = cltExamTypeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出考试类型列表
   */
  @ApiOperation("导出考试类型列表")
  @PreAuthorize(hasPermi = "collection:cltExamType:export")
  @Log(title = "考试类型", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "考试类型", required = true) CltExamType cltExamType) throws IOException {
    List<CltExamType> list = cltExamTypeService.selectCltExamTypeList(cltExamType);
    ExcelUtil<CltExamType> util = new ExcelUtil<>(CltExamType.class);
     util.exportExcel(response,list, "cltExamType");
  }

  /**
   * 获取考试类型详细信息
   */
  @ApiOperation("获取考试类型详细信息")
  @PreAuthorize(hasPermi = "collection:cltExamType:query")
  @GetMapping(value = "/{examTypeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "考试类型Id", required = true) @PathVariable("examTypeId") Long examTypeId) {
    return AjaxResult.success(cltExamTypeService.getById(examTypeId));
  }

  /**
   * 新增考试类型
   */
  @ApiOperation("新增考试类型")
  @PreAuthorize(hasPermi = "collection:cltExamType:add")
  @Log(title = "考试类型", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExamType cltExamType) {
    return toAjax(cltExamTypeService.save(cltExamType));
  }

  /**
   * 修改考试类型
   */
  @ApiOperation("修改考试类型")
  @PreAuthorize(hasPermi = "collection:cltExamType:edit")
  @Log(title = "考试类型", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "考试类型", required = true) @RequestBody CltExamType cltExamType) {
    return toAjax(cltExamTypeService.updateById(cltExamType));
  }

  /**
   * 删除考试类型
   */
  @ApiOperation("删除考试类型")
  @PreAuthorize(hasPermi = "collection:cltExamType:remove")
  @Log(title = "考试类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{examTypeIds}")
  public AjaxResult remove(
      @ApiParam(value = "考试类型Ids", required = true) @PathVariable Long
          [] examTypeIds) {
    return toAjax(cltExamTypeService.removeByIds(Arrays.asList(examTypeIds)));
  }
}
