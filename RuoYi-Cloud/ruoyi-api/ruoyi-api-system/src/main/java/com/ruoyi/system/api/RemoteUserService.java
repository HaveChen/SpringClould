package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

  /**
   * 通过用户名查询用户信息
   *
   * @param username 用户名
   * @return 结果
   */
  @GetMapping(value = "/user/info/{username}")
  public R<LoginUser> getUserInfo(@PathVariable("username") String username);

  /**
   * 通过手机号码查询用户信息
   *
   * @param phoneNumber 手机号码
   * @return 结果
   */
  @GetMapping(value = "/user/getUserByPhone/{phoneNumber}")
  public R<LoginUser> getUserInfoByPhone(@PathVariable("phoneNumber") String phoneNumber);
}
