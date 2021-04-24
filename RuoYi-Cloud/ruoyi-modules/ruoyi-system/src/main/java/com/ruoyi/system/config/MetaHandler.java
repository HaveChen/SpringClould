package com.ruoyi.system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.core.utils.SecurityUtils;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Component
public class MetaHandler implements MetaObjectHandler {

  /**
   * 新增数据执行
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createTime", new Date(), metaObject);
    this.setFieldValByName("updateTime", new Date(), metaObject);
    this.setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
    this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
  }

  /**
   * 更新数据执行
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", new Date(), metaObject);
    this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
  }
}
