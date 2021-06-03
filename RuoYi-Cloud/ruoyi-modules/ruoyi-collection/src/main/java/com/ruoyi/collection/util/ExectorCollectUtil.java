package com.ruoyi.collection.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/*
 *线程池管理：提供了2个线程池，防止提交任务和执行任务产生线程死锁
 */
@Slf4j
public class ExectorCollectUtil {

  // 针对执行线程设置：核心线程数
  private static final int corePoolSize = 32;
  // 针对执行线程设置：线程池最大线程数，如果是“非直接提交队列”，这个基本没用
  private static final int maxThreadSize = 32;
  //等待队列最大值
  private static final int maxWaitQueueSize = 100;

  /**
   * 任务提交专用线程,提交导入，提交分析
   */
  public static ExecutorService jobNewInstance() {
    return ExectorUtilJob.INSTANCE;
  }

  /**
   * 任务执行专用线程：导入具体文件，具体分析执行
   */
  public static ExecutorService taskExecutionInstance() {
    return ExectorUtilTask.INSTANCE;
  }

  private static class ExectorUtilJob {

    //构建一个有核心线程(5)，最大线程数限制(5)，有界队列的线程池(50)，使用默认拒接策略
    private static final ExecutorService INSTANCE =
        new ThreadPoolExecutor(
            5,
            maxThreadSize,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(50),
            new CntesthreadFactory("job"));
  }

  private static class ExectorUtilTask {

    //构建一个有核心线程，最大线程数限制，有界队列的线程池，使用默认拒接策略
    private static final ExecutorService INSTANCE =
        new ThreadPoolExecutor(
            corePoolSize,
            maxThreadSize,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(maxWaitQueueSize),
            new CntesthreadFactory("task"));
  }

  private ExectorCollectUtil() {
  }

  /**
   * 创建线程池工厂
   */
  static class CntesthreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final String namePrefix;
    private AtomicInteger threadSize = new AtomicInteger();

    CntesthreadFactory(String name) {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
      namePrefix = "FXPT-" + name + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(group, r, namePrefix + threadSize.getAndIncrement(), 0);
      log.info("线程池创建线程：{}", t.getName());
      // 设置非守护线程
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      // 设置线程的优先级5(低优先级)
      if (t.getPriority() != Thread.NORM_PRIORITY) {
        t.setPriority(Thread.NORM_PRIORITY);
      }
      return t;
    }
  }
}
