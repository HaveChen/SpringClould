package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.service.ICltExamDataFieldService;
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
 * 数据采集类型--考试Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "数据采集类型--考试")
@RestController
@RequestMapping("/cltDatafieldToExam")
public class CltExamDataFieldController extends BaseController {

  @Autowired
  private ICltExamDataFieldService cltExamDataFieldService;

  /**
   * 查询数据采集类型--考试列表
   */
  @ApiOperation("查询数据采集类型--考试列表")
  // @PreAuthorize(hasPermi = "collection:cltDatafieldToExam:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "数据采集类型--考试", required = true) CltExamDataField cltExamDataField) {
    startPage();
    List<CltExamDataField> list = cltExamDataFieldService
        .selectCltExamDataFieldList(cltExamDataField);
    return getDataTable(list);
  }

  /**
   * 导出数据采集类型--考试列表
   */
  @ApiOperation("导出数据采集类型--考试列表")
  //@PreAuthorize(hasPermi = "collection:cltDatafieldToExam:export")
  @Log(title = "数据采集类型--考试", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "数据采集类型--考试", required = true) CltExamDataField cltExamDataField)
      throws IOException {
    List<CltExamDataField> list = cltExamDataFieldService
        .selectCltExamDataFieldList(cltExamDataField);
    ExcelUtil<CltExamDataField> util = new ExcelUtil<>(CltExamDataField.class);
     util.exportExcel(response,list, "cltDatafieldToExam");
  }

  /**
   * 获取数据采集类型--考试详细信息
   */
  @ApiOperation("获取数据采集类型--考试详细信息")
//  @PreAuthorize(hasPermi = "collection:cltDatafieldToExam:query")
  @GetMapping(value = "/{examDataFieldId}")
  public AjaxResult getInfo(
      @ApiParam(value = "数据采集类型--考试Id", required = true) @PathVariable("examDataFieldId") Long examDataFieldId) {
    return AjaxResult.success(cltExamDataFieldService.getById(examDataFieldId));
  }

  /**
   * 新增数据采集类型--考试
   */
  @ApiOperation("新增数据采集类型--考试")
  // @PreAuthorize(hasPermi = "collection:cltDatafieldToExam:add")
  @Log(title = "数据采集类型--考试", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExamDataField cltExamDataField) {
    return toAjax(cltExamDataFieldService.save(cltExamDataField));
  }

  /**
   * 修改数据采集类型--考试
   */
  @ApiOperation("修改数据采集类型--考试")
  // @PreAuthorize(hasPermi = "collection:cltDatafieldToExam:edit")
  @Log(title = "数据采集类型--考试", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "数据采集类型--考试", required = true) @RequestBody CltExamDataField cltExamDataField) {
    return toAjax(cltExamDataFieldService.updateById(cltExamDataField));
  }

  /**
   * 删除数据采集类型--考试
   */
  @ApiOperation("删除数据采集类型--考试")
  //@PreAuthorize(hasPermi = "collection:cltDatafieldToExam:remove")
  @Log(title = "数据采集类型--考试", businessType = BusinessType.DELETE)
  @DeleteMapping("/{examDataFieldIds}")
  public AjaxResult remove(
      @ApiParam(value = "数据采集类型--考试Ids", required = true) @PathVariable Long
          [] examDataFieldIds) {
    return toAjax(cltExamDataFieldService.removeByIds(Arrays.asList(examDataFieldIds)));
  }
}
