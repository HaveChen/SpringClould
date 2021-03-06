package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysConfig;
import java.util.List;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.data.redis.cache.RedisCache;

/**
 * 参数配置 数据层
 *
 * @author ruoyi
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

  /**
   * 查询参数配置信息
   *
   * @param config 参数配置信息
   * @return 参数配置信息
   */
  public SysConfig selectConfig(SysConfig config);

  /**
   * 查询参数配置列表
   *
   * @param config 参数配置信息
   * @return 参数配置集合
   */
  public List<SysConfig> selectConfigList(SysConfig config);

  /**
   * 根据键名查询参数配置信息
   *
   * @param configKey 参数键名
   * @return 参数配置信息
   */
  public SysConfig checkConfigKeyUnique(String configKey);


}
