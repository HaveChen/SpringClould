package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltDataType;
import com.ruoyi.collection.service.ICltDataTypeService;
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
 * 数据采集类型Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "数据采集类型")
@RestController
@RequestMapping("/cltDataType")
public class CltDataTypeController extends BaseController {

  @Autowired
  private ICltDataTypeService cltDataTypeService;

  /**
   * 查询数据采集类型列表
   */
  @ApiOperation("查询数据采集类型列表")
  @PreAuthorize(hasPermi = "collection:cltDataType:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "数据采集类型", required = true) CltDataType cltDataType) {
    startPage();
    List<CltDataType> list = cltDataTypeService.selectCltDataTypeList(cltDataType);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：采集类型
   */
  @ApiOperation("初始化下拉数据：采集类型")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltDataType> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltDataType> lists = cltDataTypeService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出数据采集类型列表
   */
  @ApiOperation("导出数据采集类型列表")
  @PreAuthorize(hasPermi = "collection:cltDataType:export")
  @Log(title = "数据采集类型", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "数据采集类型", required = true) CltDataType cltDataType) throws IOException {
    List<CltDataType> list = cltDataTypeService.selectCltDataTypeList(cltDataType);
    ExcelUtil<CltDataType> util = new ExcelUtil<>(CltDataType.class);
     util.exportExcel(response,list, "cltDataType");
  }

  /**
   * 获取数据采集类型详细信息
   */
  @ApiOperation("获取数据采集类型详细信息")
  @PreAuthorize(hasPermi = "collection:cltDataType:query")
  @GetMapping(value = "/{dataTypeId}")
  public AjaxResult getInfo(
      @ApiParam(value = "数据采集类型Id", required = true) @PathVariable("dataTypeId") Long dataTypeId) {
    return AjaxResult.success(cltDataTypeService.getById(dataTypeId));
  }

  /**
   * 新增数据采集类型
   */
  @ApiOperation("新增数据采集类型")
  @PreAuthorize(hasPermi = "collection:cltDataType:add")
  @Log(title = "数据采集类型", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltDataType cltDataType) {
    return toAjax(cltDataTypeService.save(cltDataType));
  }

  /**
   * 修改数据采集类型
   */
  @ApiOperation("修改数据采集类型")
  @PreAuthorize(hasPermi = "collection:cltDataType:edit")
  @Log(title = "数据采集类型", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "数据采集类型", required = true) @RequestBody CltDataType cltDataType) {
    return toAjax(cltDataTypeService.updateById(cltDataType));
  }

  /**
   * 删除数据采集类型
   */
  @ApiOperation("删除数据采集类型")
  @PreAuthorize(hasPermi = "collection:cltDataType:remove")
  @Log(title = "数据采集类型", businessType = BusinessType.DELETE)
  @DeleteMapping("/{dataTypeIds}")
  public AjaxResult remove(
      @ApiParam(value = "数据采集类型Ids", required = true) @PathVariable Long
          [] dataTypeIds) {
    return toAjax(cltDataTypeService.removeByIds(Arrays.asList(dataTypeIds)));
  }
}
