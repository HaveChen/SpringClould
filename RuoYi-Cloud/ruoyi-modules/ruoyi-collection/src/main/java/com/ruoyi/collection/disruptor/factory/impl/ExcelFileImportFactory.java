package com.ruoyi.collection.disruptor.factory.impl;

import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;
import com.ruoyi.collection.disruptor.factory.ImportFileFactory;
import com.ruoyi.collection.domain.CltProgress;
import java.util.Date;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

/*
 *chenyou
 *2020/8/14  15:23
 */
@Slf4j
public class ExcelFileImportFactory implements ImportFileFactory {

  private Job importStudentJob;
  private Job importCjJob;
  private Job importCourseJob;
  private JobLauncher jobLauncher;
  private Long startDatetime;

  private ExcelFileImportFactory() {
  }

  public ExcelFileImportFactory(Map<String, Object> serviceMap) {
    this.importStudentJob = (Job) serviceMap.get("importStudentJob");
    this.importCjJob = (Job) serviceMap.get("importCjJob");
    this.importCourseJob = (Job) serviceMap.get("importCourseJob");
    this.jobLauncher = (JobLauncher) serviceMap.get("jobLauncher");
    startDatetime = new Date().getTime();
  }

  @Override
  public void importExamFile(ExamFileImportEvent fileImportEvent) throws Exception {
    CltProgress fileImport = fileImportEvent.getFileImport();
    String fileType = fileImport.getFileType();
    if ("XLSX".equalsIgnoreCase(fileType) || "XLS".equalsIgnoreCase(fileType)) {
      //设置文件等待导入
      fileImportEvent.getCltProgressService().updateImportStatus(fileImport.getProgressId(), "3");
      importExcelFile(fileImport);
    } else {
      log.error("暂时不支持除XLSX，XLS以外的文件导入。当前文件类型{}", fileType);
    }
  }

  /**
   * Excel文件导入
   */
  private void importExcelFile(CltProgress fileImport) throws Exception {
    //控制唯一
    Date startTime = new Date(startDatetime + fileImport.getProgressId() * 5999);
    JobParameters jobParameters =
        new JobParametersBuilder()
            .addDate("date", startTime)
            .addLong("progress.id", fileImport.getProgressId())
            .toJobParameters();
    if ("bmk".equalsIgnoreCase(fileImport.getImportType())) {
      jobLauncher.run(importStudentJob, jobParameters);
    } else if ("cj".equalsIgnoreCase(fileImport.getImportType())) {
      jobLauncher.run(importCjJob, jobParameters);
    } else if ("course".equalsIgnoreCase(fileImport.getImportType())) {
      jobLauncher.run(importCourseJob, jobParameters);
    }
    Date endTime = new Date();
    log.debug(
        "{}导入花费时间{}秒", fileImport.getFileName(), (endTime.getTime() - startTime.getTime()) / 1000);
  }
}
