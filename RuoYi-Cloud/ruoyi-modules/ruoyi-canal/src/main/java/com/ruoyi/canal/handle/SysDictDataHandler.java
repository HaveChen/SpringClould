package com.ruoyi.canal.handle;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * 数据字典操作
 */
@Component
@CanalTable(value = "sys_dict_data")
public class SysDictDataHandler implements EntryHandler<SysDictData> {

  @Autowired
  private RedisService redisService;

  @Override
  public void insert(SysDictData sysDictData) {

  }

  @Override
  public void update(SysDictData before, SysDictData after) {

  }

  @Override
  public void delete(SysDictData sysDictData) {

  }
}
