package com.ruoyi.standard.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

;

@EnableTransactionManagement(proxyTargetClass = true) // 开启事务
@Configuration
public class MybatisPlusConfig {

  /*
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 创建SQL解析器集合
    List<ISqlParser> sqlParserList = new ArrayList<>();
    // 创建租户SQL解析器
    TenantSqlParser tenantSqlParser = new TenantSqlParser();
    // 设置租户处理器
    tenantSqlParser.setTenantHandler(new MyTenantLineHandler());
    sqlParserList.add(tenantSqlParser);
    paginationInterceptor.setSqlParserList(sqlParserList);
    return paginationInterceptor;
  }

  /**
   * 乐观锁插件
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

  //     @Bean
  //     public MetaObjectHandler metaObjectHandler() {
  //         return new CustomMetaObjectHandler();
  //     }

  /**
   * 自动填充功能
   */
  @Bean
  public GlobalConfig globalConfig() {
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setMetaObjectHandler(new MetaHandler());
    return globalConfig;
  }
}
