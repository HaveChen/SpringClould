package com.ruoyi.collection.util;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.Util;

public class BuildDisruptorUtils<T> {


  public Disruptor<T> getDisruptor(EventFactory<T> factory) {
    return getDisruptor(8, factory);
  }

  public Disruptor<T> getDisruptor(int num, EventFactory<T> factory) {
    int taskBuffer = Util.ceilingNextPowerOfTwo(num);
    Disruptor<T> disruptor =
        new Disruptor<>(
            factory,
            taskBuffer,
            ExectorCollectUtil.taskExecutionInstance(),
            ProducerType.SINGLE,
            new YieldingWaitStrategy());
    return disruptor;
  }
}
