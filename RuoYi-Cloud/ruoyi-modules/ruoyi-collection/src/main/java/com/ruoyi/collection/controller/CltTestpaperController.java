package com.ruoyi.collection.controller;

import com.ruoyi.collection.domain.CltTestpaper;
import com.ruoyi.collection.service.ICltTestpaperService;
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
 * 试卷Controller
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Slf4j
@Api(tags = "试卷")
@RestController
@RequestMapping("/cltTestpaper")
public class CltTestpaperController extends BaseController {

  @Autowired
  private ICltTestpaperService cltTestpaperService;

  /**
   * 查询试卷列表
   */
  @ApiOperation("查询试卷列表")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:list")
  @GetMapping("/list")
  public TableDataInfo list(
      @ApiParam(value = "试卷", required = true) CltTestpaper cltTestpaper) {
    startPage();
    List<CltTestpaper> list = cltTestpaperService.selectCltTestpaperList(cltTestpaper);
    return getDataTable(list);
  }

  /**
   * 导出试卷列表
   */
  @ApiOperation("导出试卷列表")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:export")
  @Log(title = "试卷", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public void export(HttpServletResponse response,
      @ApiParam(value = "试卷", required = true) CltTestpaper cltTestpaper) throws IOException {
    List<CltTestpaper> list = cltTestpaperService.selectCltTestpaperList(cltTestpaper);
    ExcelUtil<CltTestpaper> util = new ExcelUtil<>(CltTestpaper.class);
     util.exportExcel(response,list, "cltTestpaper");
  }

  /**
   * 获取试卷详细信息
   */
  @ApiOperation("根据考试获取试卷：试卷下拉")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:testpaperList")
  @GetMapping(value = "getTestPaperList/{examId}")
  public AjaxResult getTestPaperList(
      @ApiParam(value = "考试Id", required = true) @PathVariable("examId") String examId) {
    return AjaxResult.success(cltTestpaperService.getTestPaperByExamId(examId));
  }

  /**
   * 获取试卷详细信息
   */
  @ApiOperation("获取试卷详细信息")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:query")
  @GetMapping(value = "/{testpaperId}")
  public AjaxResult getInfo(
      @ApiParam(value = "试卷Id", required = true) @PathVariable("testpaperId") Long testpaperId) {
    return AjaxResult.success(cltTestpaperService.getById(testpaperId));
  }

  /**
   * 新增试卷
   */
  @ApiOperation("新增试卷")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:add")
  @Log(title = "试卷", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody CltTestpaper cltTestpaper) {
    return toAjax(cltTestpaperService.save(cltTestpaper));
  }

  /**
   * 修改试卷
   */
  @ApiOperation("修改试卷")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:edit")
  @Log(title = "试卷", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(
      @ApiParam(value = "试卷", required = true) @RequestBody CltTestpaper cltTestpaper) {
    return toAjax(cltTestpaperService.updateById(cltTestpaper));
  }

  /**
   * 删除试卷
   */
  @ApiOperation("删除试卷")
  //@PreAuthorize(hasPermi = "collection:cltTestpaper:remove")
  @Log(title = "试卷", businessType = BusinessType.DELETE)
  @DeleteMapping("/{testpaperIds}")
  public AjaxResult remove(
      @ApiParam(value = "试卷Ids", required = true) @PathVariable Long
          [] testpaperIds) {
    return toAjax(cltTestpaperService.removeByIds(Arrays.asList(testpaperIds)));
  }

  /**
   * 根据考试Id删除试卷
   */
  @ApiOperation("按照考试删除试卷")
  @Log(title = "试卷", businessType = BusinessType.DELETE)
  @DeleteMapping("testpaperDelete/{examId}")
  public AjaxResult studentDelete(
      @ApiParam(value = "考试Id", required = true) @PathVariable String examId) {
    log.debug("正在删除考试{}的试卷数据。", examId);
    return toAjax(cltTestpaperService.deleteTestpaperByexamId(examId));
  }
}
