package com.ruoyi.collection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.collection.domain.CltExamInfo;
import com.ruoyi.collection.service.ICltExamInfoService;
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
import lombok.extern.slf4j.Slf4j;
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
 * 考试信息Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Api(tags = "考试信息")
@RestController
@RequestMapping("/cltExamInfo")
@Slf4j
public class CltExamInfoController extends BaseController {

  @Autowired
  private ICltExamInfoService cltExamInfoService;

  /**
   * 查询考试信息列表
   */
  @ApiOperation("查询考试信息列表")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "考试信息", required = true) CltExamInfo cltExamInfo) {
    startPage();
    List<CltExamInfo> list = cltExamInfoService.selectCltExamInfoList(cltExamInfo);
    return getDataTable(list);
  }

  /**
   * 查询有效下拉数据：考试
   */
  @ApiOperation("初始化下拉数据：考试")
  @GetMapping("/listFiltrateData")
  public AjaxResult listFiltrateData() {
    QueryWrapper<CltExamInfo> qw = new QueryWrapper<>();
    qw.eq("status", 0);
    List<CltExamInfo> lists = cltExamInfoService.list(qw);
    return AjaxResult.success(lists);
  }

  /**
   * 根据届别获取考试下拉
   */
  @ApiOperation("根据届别代码获取考试下拉")
  @GetMapping(value = "getExamList/{graduateCode}")
  public AjaxResult getExamList(
      @ApiParam(value = "届别Id", required = true) @PathVariable("graduateCode") String graduateCode) {
    return AjaxResult.success(cltExamInfoService.getExamByGraduateCode(graduateCode));
  }

  /**
   * 导出考试信息列表
   */
  @ApiOperation("导出考试信息列表")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:export")
  @Log(title = "考试信息", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "考试信息", required = true) CltExamInfo cltExamInfo) throws IOException {
    List<CltExamInfo> list = cltExamInfoService.selectCltExamInfoList(cltExamInfo);
    ExcelUtil<CltExamInfo> util = new ExcelUtil<>(CltExamInfo.class);
     util.exportExcel(response,list, "cltExamInfo");
  }

  /**
   * 获取考试信息详细信息
   */
  @ApiOperation("获取考试信息详细信息")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:query")
  @GetMapping(value = "/{examId}")
  public AjaxResult getInfo(
      @ApiParam(value = "考试信息Id", required = true) @PathVariable("examId") Long examId) {
    return AjaxResult.success(cltExamInfoService.getById(examId));
  }

  /**
   * 新增考试信息
   */
  @ApiOperation("新增考试信息")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:add")
  @Log(title = "考试信息", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltExamInfo cltExamInfo) {
    return toAjax(cltExamInfoService.saveExamInfo(cltExamInfo));
  }

  /**
   * 修改考试信息
   */
  @ApiOperation("修改考试信息")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:edit")
  @Log(title = "考试信息", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "考试信息", required = true) @RequestBody CltExamInfo cltExamInfo) {
    return toAjax(cltExamInfoService.updateById(cltExamInfo));
  }

  /**
   * 删除考试信息
   */
  @ApiOperation("删除考试信息")
  @PreAuthorize(hasPermi = "collection:cltExamInfo:remove")
  @Log(title = "考试信息", businessType = BusinessType.DELETE)
  @DeleteMapping("/{examIds}")
  public AjaxResult remove(
      @ApiParam(value = "考试信息Ids", required = true) @PathVariable Long
          [] examIds) {
    return toAjax(cltExamInfoService.removeExamsByIds(Arrays.asList(examIds)));
  }
}
