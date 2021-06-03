package com.ruoyi.collection.bacth.config;

import com.ruoyi.collection.bacth.processor.CltStudentProcessor;
import com.ruoyi.collection.bacth.reader.CltStudentReader;
import com.ruoyi.collection.bacth.reader.ExcelItemReader;
import com.ruoyi.collection.bacth.writer.CltStudentWriter;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltStudentDetail;
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
public class CltStudentImportJobConfiguration {

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
  private int chunkSize = 50;

  /**
   * 报名库采集JOB
   */
  @Bean
  public Job importStudentJob(Step importStudentStep) {
    return jobBuilderFactory
        .get("importStudentJob")
        .incrementer(new RunIdIncrementer())
        .flow(importStudentStep)
        .end()
        .build();
  }

  @Bean
  public Step importStudentStep(ItemReader<CltStudentDetail> studentFileReader) {
    return stepBuilderFactory
        .get("importStudentStep")
        .<CltStudentDetail, CltStudentDetail>chunk(chunkSize)
        .reader(studentFileReader)
        .processor(studentFileProcessor())
        .writer(studentFileWriter())
        .build();
  }

  @StepScope
  @Bean
  public ExcelItemReader studentFileReader(
      @Value("#{jobParameters['progress.id']}") Long progressId,
      @Value("#{jobParameters['date']}") Date date) {
    cltProgress = cltProgressService.getById(progressId);
    this.examId = cltProgress.getExamId() + "";
    //根据导入类型查询导入配置信息
    etlDatafields = cltExamDataFieldService
        .selectCltExamDataFillds(examId, cltProgress.getImportType());
    examSubjects =null; //remoteStandardService.getSubjects();
    return new CltStudentReader(etlDatafields, cltProgress)
        .initItemReader();
  }

  @StepScope
  @Bean
  public CltStudentProcessor studentFileProcessor() {
    CltStudentProcessor cltStudentProcessor = new CltStudentProcessor(examId);
    cltStudentProcessor.setFiledMap(etlDatafields);
    cltStudentProcessor.setStujectMap(examSubjects);
    return cltStudentProcessor;
  }


  @StepScope
  @Bean
  public CltStudentWriter studentFileWriter() {
    return new CltStudentWriter(cltProgress);
  }
}
