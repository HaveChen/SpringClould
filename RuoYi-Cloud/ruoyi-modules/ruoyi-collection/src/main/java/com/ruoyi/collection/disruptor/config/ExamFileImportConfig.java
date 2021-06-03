package com.ruoyi.collection.disruptor.config;

import com.lmax.disruptor.dsl.Disruptor;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;
import com.ruoyi.collection.disruptor.factory.ExamFileImportFactory;
import com.ruoyi.collection.disruptor.handler.ExamFileImportEndHandler;
import com.ruoyi.collection.disruptor.handler.ExamFileImportHandler;
import com.ruoyi.collection.disruptor.produce.ExamFileImportProduce;
import com.ruoyi.collection.disruptor.service.IExamFileImportContext;
import com.ruoyi.collection.disruptor.service.impl.ExamFileImportContext;
import com.ruoyi.collection.domain.CltExamInfo;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltTestpaper;
import com.ruoyi.collection.service.ICltExamInfoService;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
import com.ruoyi.collection.util.BuildDisruptorUtils;
import com.ruoyi.collection.util.ExectorCollectUtil;
import com.ruoyi.standard.api.domain.StdSubject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *组装任务
 */
@Slf4j
public class ExamFileImportConfig implements Runnable {

  private CltExamInfo examInfo;
  private IExamFileImportContext fileImportContext;
  @Autowired
  private ICltExamInfoService cltExamInfoService;
  @Autowired
  private ICltProgressService cltProgressService;
  @Autowired
  private ICltTestpaperService cltTestpaperService;

  public ExamFileImportConfig(String examId, Map<String, Object> serviceMap)
      throws Exception {
    cltExamInfoService = (ICltExamInfoService) serviceMap.get("cltExamInfoService");
    cltProgressService = (ICltProgressService) serviceMap.get("cltProgressService");
    cltTestpaperService = (ICltTestpaperService) serviceMap.get("cltTestpaperService");
    fileImportContext =
        new ExamFileImportContext(
            cltProgressService.getExamFileImportsByExamId(examId, "1"), serviceMap);
    examInfo = cltExamInfoService.getById(examId);
  }

  @Override
  public void run() {
    // 开始时间
    Long startTime = new Date().getTime();
    try {
      //初始化试卷
      initTestPaper();
      //开始组装导入相关事件
      startImportExamFile();
      //初始化参加考试的组织
      initExamIncludeDeptCode();
      success();
    } catch (Exception e) {
      error();
    } finally {
      Long endTime = new Date().getTime();
      // 文件导入结束，花费事件
      log.info("{}文件导入结束,耗时{}秒", examInfo.getExamName(), (endTime - startTime) / 1000);
    }
  }

  /**
   * 初始化参加考试的科目
   */
  private void initExamIncludeDeptCode() {
    List<CltProgress> cltProcess = fileImportContext.getCltProcess();
    List<CltProgress> bmkProcess = cltProcess.stream().filter(
        f -> "bmk".equalsIgnoreCase(f.getImportType())).collect(Collectors.toList());
    if (bmkProcess.size() > 0) {
      //存在报名库的导入，更加报名库修改参加考试的组织
      log.info("存在报名库的导入，更加报名库修改参加考试的组织.");
      cltExamInfoService.initExamIncludeDeptCode(examInfo);
    }

  }

  /**
   * 如果有成绩或者细目表导入的话，需要初始化试卷
   */
  private void initTestPaper() {
    List<CltProgress> cltProcess = fileImportContext.getCltProcess();
    List<StdSubject> stdSubjects = null;//stdSubjectService.list();
    Map<String, StdSubject> subjectMap = stdSubjects.stream()
        .collect(Collectors.toMap(StdSubject::getSubjectCode, s -> s, (k1, k2) -> k1));
    List<CltProgress> testProcess = cltProcess.stream().filter(
        f -> "cj".equalsIgnoreCase(f.getImportType()) || "xmb".equalsIgnoreCase(f.getImportType()))
        .collect(
            Collectors.toList());
    StdSubject stdSubject = null;
    for (CltProgress process : testProcess) {
      if (subjectMap.containsKey(process.getSubjectCode())) {
        stdSubject = subjectMap.get(process.getSubjectCode());
      }
      getCltTestpaper(process, stdSubject);
    }
  }

  private void getCltTestpaper(CltProgress process, StdSubject stdSubject) {
    CltTestpaper cltTestpaper = cltTestpaperService
        .getTestPaperByExamIdAndSubjectCode(process.getExamId() + "",
            process.getSubjectCode());
    // 无试卷时，初始化新试卷
    if (cltTestpaper == null) {
      cltTestpaper = new CltTestpaper();
      cltTestpaper.setExamId(process.getExamId());
      cltTestpaper.setTestpaperName(
          stdSubject == null ? process.getSubjectCode() : stdSubject.getSubjectName());
      cltTestpaper.setSubjectCode(process.getSubjectCode());
      cltTestpaper.setHasCj(1L);
      cltTestpaper.setHasItem(1L);
      cltTestpaperService.save(cltTestpaper);
    }
  }

  /**
   * 开始组装导入相关事件
   */
  private void startImportExamFile() {
    try {
      ExamFileImportProduce efp = new ExamFileImportProduce(fileImportContext);
      CountDownLatch countDownLatch = efp.getCountDownLatch();
      //设置数组长度
      Disruptor<ExamFileImportEvent> disruptor = buildDisruptor(64);
      efp.setDisruptor(disruptor);
      // 默认开启4个消费者
      //TODO 暂时改成单线程导入，springbatch job 多线程执行同一个单例的job暂时还存在问题，需要优化
      disruptor
          .handleEventsWithWorkerPool(
              //new ExamFileImportHandler(),
              //new ExamFileImportHandler(),
              //new ExamFileImportHandler(),
              new ExamFileImportHandler())
          .then(new ExamFileImportEndHandler(countDownLatch));
      disruptor.start();
      ExectorCollectUtil.taskExecutionInstance().execute(efp);
      countDownLatch.await();
      disruptor.shutdown();
    } catch (Exception e) {
      log.info("文件导入任务构建失败");
      e.printStackTrace();
      throw new RuntimeException("文件导入任务构建失败:", e);
    }
  }

  private Disruptor<ExamFileImportEvent> buildDisruptor(int i) {
    BuildDisruptorUtils<ExamFileImportEvent> build = new BuildDisruptorUtils<>();
    return build.getDisruptor(i, new ExamFileImportFactory());
  }

  /**
   * 文件导入完成，更新标识
   */
  private void success() {
    examInfo.setStatus(3L);
    cltExamInfoService.updateById(examInfo);
  }

  /**
   * 文件导入失败，更新标识
   */
  private void error() {
    examInfo.setStatus(4L);
    cltExamInfoService.updateById(examInfo);
  }
}
