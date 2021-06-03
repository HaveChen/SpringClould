package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltDataField;
import com.ruoyi.collection.service.ICltDataFieldService;
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
 * 数据采集类型Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "数据采集明细")
@RestController
@RequestMapping("/cltDataField")
public class CltDataFieldController extends BaseController {

  @Autowired
  private ICltDataFieldService cltDataFieldService;

  /**
   * 查询数据采集类型列表
   */
  @ApiOperation("查询数据采集明细列表")
  // @PreAuthorize(hasPermi = "collection:cltDataField:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "数据采集明细", required = true) CltDataField cltDataField) {
    startPage();
    List<CltDataField> list = cltDataFieldService.selectCltDataFieldList(cltDataField);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：采集字段类型
   */
  @ApiOperation("初始化下拉数据：采集字段明细")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltDataField> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltDataField> lists = cltDataFieldService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 导出数据采集类型列表
   */
  @ApiOperation("导出数据采集明细列表")
  //@PreAuthorize(hasPermi = "collection:cltDataField:export")
  @Log(title = "数据采集明细", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "数据采集明细", required = true) CltDataField cltDataField) throws IOException {
    List<CltDataField> list = cltDataFieldService.selectCltDataFieldList(cltDataField);
    ExcelUtil<CltDataField> util = new ExcelUtil<CltDataField>(CltDataField.class);
     util.exportExcel(response,list, "cltDataField");
  }

  /**
   * 获取数据采集类型详细信息
   */
  @ApiOperation("获取数据采集明细详细信息")
  //@PreAuthorize(hasPermi = "collection:cltDataField:query")
  @GetMapping(value = "/{dataFieldId}")
  public AjaxResult getInfo(
      @ApiParam(value = "数据采集明细Id", required = true) @PathVariable("dataFieldId") Long dataFieldId) {
    return AjaxResult.success(cltDataFieldService.getById(dataFieldId));
  }

  /**
   * 新增数据采集类型
   */
  @ApiOperation("新增数据采集明细")
  //@PreAuthorize(hasPermi = "collection:cltDataField:add")
  @Log(title = "数据采集明细", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltDataField cltDataField) {
    return toAjax(cltDataFieldService.save(cltDataField));
  }

  /**
   * 修改数据采集类型
   */
  @ApiOperation("修改数据采集明细")
  //@PreAuthorize(hasPermi = "collection:cltDataField:edit")
  @Log(title = "数据采集明细", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "数据采集明细", required = true) @RequestBody CltDataField cltDataField) {
    return toAjax(cltDataFieldService.updateById(cltDataField));
  }

  /**
   * 删除数据采集类型
   */
  @ApiOperation("删除数据采集明细")
  // @PreAuthorize(hasPermi = "collection:cltDataField:remove")
  @Log(title = "数据采集明细", businessType = BusinessType.DELETE)
  @DeleteMapping("/{dataFieldIds}")
  public AjaxResult remove(
      @ApiParam(value = "数据采集明细Ids", required = true) @PathVariable Long
          [] dataFieldIds) {
    return toAjax(cltDataFieldService.removeByIds(Arrays.asList(dataFieldIds)));
  }
}
