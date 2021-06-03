package com.ruoyi.collection.bacth.config;

import com.ruoyi.collection.bacth.processor.CltCourseProcessor;
import com.ruoyi.collection.bacth.reader.CltCourseReader;
import com.ruoyi.collection.bacth.reader.ExcelItemReader;
import com.ruoyi.collection.bacth.writer.CltCourseWriter;
import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltExamDataFieldService;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.standard.api.domain.StdSubject;
import java.util.Date;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CltCourseImportJobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private ICltExamDataFieldService cltExamDataFieldService;
  @Autowired
  private ICltProgressService cltProgressService;

  private String examId;
  private CltProgress cltProgress;
  private List<CltExamDataField> etlDatafields;
  private List<StdSubject> examSubjects;
  private int chunkSize = 5000;

  /**
   * 报名库采集JOB
   */
  @Bean
  public Job importCourseJob(Step importCourseStep) {
    return jobBuilderFactory
        .get("importCourseJob")
        .incrementer(new RunIdIncrementer())
        .flow(importCourseStep)
        .end()
        .build();
  }

  @Bean
  public Step importCourseStep(ItemReader<CltCourse> courseFileReader) {
    return stepBuilderFactory
        .get("importCourseStep")
        .<CltCourse, CltCourse>chunk(chunkSize)
        .reader(courseFileReader)
        .processor(courseFileProcessor())
        .writer(courseFileWriter())
        .build();
  }

  @StepScope
  @Bean
  public ExcelItemReader courseFileReader(
      @Value("#{jobParameters['progress.id']}") Long progressId,
      @Value("#{jobParameters['date']}") Date date) {
    cltProgress = cltProgressService.getById(progressId);
    this.examId = cltProgress.getExamId() + "";
    //根据导入类型查询导入配置信息
    etlDatafields = cltExamDataFieldService
        .selectCltExamDataFillds(examId, cltProgress.getImportType());
    examSubjects = null;// remoteStandardService.getSubjects();
    return new CltCourseReader(etlDatafields, cltProgress)
        .initItemReader();
  }

  @StepScope
  @Bean
  public CltCourseProcessor courseFileProcessor() {
    CltCourseProcessor cltCourseProcessor = new CltCourseProcessor(examId);
    cltCourseProcessor.setFiledMap(etlDatafields);
    return cltCourseProcessor;
  }


  @StepScope
  @Bean
  public CltCourseWriter courseFileWriter() {
    return new CltCourseWriter(cltProgress);
  }
}
