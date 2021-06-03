package com.ruoyi.collection.bacth.config;

import com.ruoyi.collection.bacth.processor.CltCjProcessor;
import com.ruoyi.collection.bacth.reader.CltCjReader;
import com.ruoyi.collection.bacth.reader.ExcelItemReader;
import com.ruoyi.collection.bacth.writer.CltCjWriter;
import com.ruoyi.collection.domain.CltCjDetail;
import com.ruoyi.collection.domain.CltExamDataField;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltTestpaper;
import com.ruoyi.collection.service.ICltExamDataFieldService;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltTestpaperService;
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
public class CltCjImportJobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private ICltExamDataFieldService cltExamDataFieldService;
  @Autowired
  private ICltProgressService cltProgressService;
  @Autowired
  private ICltTestpaperService cltTestpaperService;

  private String examId;
  private CltProgress cltProgress;
  private List<CltExamDataField> etlDatafields;
  private StdSubject stdSubject;
  private CltTestpaper cltTestpaper;
  private int chunkSize = 5000;

  /**
   * 成绩采集JOB
   */
  @Bean
  public Job importCjJob(Step importCjStep) {
    return jobBuilderFactory
        .get("importCjJob")
        .incrementer(new RunIdIncrementer())
        .flow(importCjStep)
        .end()
        .build();
  }

  @Bean
  public Step importCjStep(ItemReader<CltCjDetail> cjItemReader) {
    return stepBuilderFactory
        .get("importCjStep")
        .<CltCjDetail, CltCjDetail>chunk(chunkSize)
        .reader(cjItemReader)
        .processor(cjItemProcessor())
        .writer(cjItemWriter())
        .build();
  }

  @StepScope
  @Bean
  public ExcelItemReader cjItemReader(@Value("#{jobParameters['progress.id']}") Long progressId,
      @Value("#{jobParameters['date']}") Date date) {
    cltProgress = cltProgressService.getById(progressId);
    this.examId = cltProgress.getExamId() + "";
    String subjectCode = cltProgress.getSubjectCode();
    // 根据导入类型查询导入配置信息
    etlDatafields =
        cltExamDataFieldService.selectCltExamDataFillds(examId, cltProgress.getImportType());
    // 科目
    stdSubject =null;// stdSubjectService.selectStdSubjectBySubjectCode(subjectCode);
    // 试卷
    cltTestpaper = cltTestpaperService.getTestPaperByExamIdAndSubjectCode(examId, subjectCode);
    return new CltCjReader(etlDatafields, cltProgress).initItemReader();
  }

  @StepScope
  @Bean
  public CltCjProcessor cjItemProcessor() {
    CltCjProcessor cltCjProcessor = new CltCjProcessor(cltTestpaper);
    cltCjProcessor.setFiledMap(etlDatafields);
    return cltCjProcessor;
  }

  @StepScope
  @Bean
  public CltCjWriter cjItemWriter() {
    return new CltCjWriter(cltProgress);
  }
}
