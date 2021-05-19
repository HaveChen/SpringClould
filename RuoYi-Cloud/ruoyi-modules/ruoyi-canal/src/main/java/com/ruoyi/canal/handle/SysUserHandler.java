package com.ruoyi.canal.handle;

import com.ruoyi.system.api.domain.SysUser;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * 用户表
 */
@Component
@CanalTable(value = "sys_user")
public class SysUserHandler implements EntryHandler<SysUser> {

  @Override
  public void insert(SysUser sysUser) {
    System.out.println("insert" + sysUser.toString());
  }

  @Override
  public void update(SysUser before, SysUser after) {
    System.out.println("update" + after.toString());
  }

  @Override
  public void delete(SysUser sysUser) {
    System.out.println("delete" + sysUser.toString());
  }
}
