package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltCjDetail;
import com.ruoyi.collection.service.ICltCjDetailService;
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
 * 成绩数据采集Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Slf4j
@Api(tags = "成绩数据采集")
@RestController
@RequestMapping("/cjDetail")
public class CltCjDetailController extends BaseController {

  @Autowired
  private ICltCjDetailService cltCjDetailService;

  /**
   * 查询成绩数据采集列表
   */
  @ApiOperation("查询成绩数据采集列表")
  //@PreAuthorize(hasPermi = "collection:cjDetail:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "成绩数据采集", required = true) CltCjDetail cltCjDetail) {
    startPage();
    List<CltCjDetail> list = cltCjDetailService.selectCltCjDetailList(cltCjDetail);
    return getDataTable(list);
  }

  /**
   * 导出成绩数据采集列表
   */
  @ApiOperation("导出成绩数据采集列表")
  //@PreAuthorize(hasPermi = "collection:cjDetail:export")
  @Log(title = "成绩数据采集", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "成绩数据采集", required = true) CltCjDetail cltCjDetail) throws IOException {
    List<CltCjDetail> list = cltCjDetailService.selectCltCjDetailList(cltCjDetail);
    ExcelUtil<CltCjDetail> util = new ExcelUtil<CltCjDetail>(CltCjDetail.class);
     util.exportExcel(response,list, "cjDetail");
  }

  /**
   * 获取成绩数据采集详细信息
   */
  @ApiOperation("获取成绩数据采集详细信息")
  //@PreAuthorize(hasPermi = "collection:cjDetail:query")
  @GetMapping(value = "/{cjId}")
  public AjaxResult getInfo(
      @ApiParam(value = "成绩数据采集Id", required = true) @PathVariable("cjId") String cjId) {
    return AjaxResult.success(cltCjDetailService.getById(cjId));
  }

  /**
   * 新增成绩数据采集
   */
  @ApiOperation("新增成绩数据采集")
  //@PreAuthorize(hasPermi = "collection:cjDetail:add")
  @Log(title = "成绩数据采集", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltCjDetail cltCjDetail) {
    return toAjax(cltCjDetailService.save(cltCjDetail));
  }

  /**
   * 修改成绩数据采集
   */
  @ApiOperation("修改成绩数据采集")
  //@PreAuthorize(hasPermi = "collection:cjDetail:edit")
  @Log(title = "成绩数据采集", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "成绩数据采集", required = true) @RequestBody CltCjDetail cltCjDetail) {
    return toAjax(cltCjDetailService.updateById(cltCjDetail));
  }

  /**
   * 删除成绩数据采集
   */
  @ApiOperation("删除成绩数据采集")
  // @PreAuthorize(hasPermi = "collection:cjDetail:remove")
  @Log(title = "成绩数据采集", businessType = BusinessType.DELETE)
  @DeleteMapping("/{cjIds}")
  public AjaxResult remove(
      @ApiParam(value = "成绩数据采集Ids", required = true) @PathVariable String
          [] cjIds) {
    return toAjax(cltCjDetailService.removeByIds(Arrays.asList(cjIds)));
  }

  /**
   * 根据考试Id删除成绩
   */
  @ApiOperation("按照考试删除成绩")
  @Log(title = "成绩", businessType = BusinessType.DELETE)
  @DeleteMapping("cjDelete/{examId}")
  public AjaxResult cjDelete(
      @ApiParam(value = "考试Id", required = true) @PathVariable String examId) {
    log.debug("正在删除考试{}的试卷数据。", examId);
    return toAjax(cltCjDetailService.deleteCjByexamId(examId));
  }
}
