package com.ruoyi.collection.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.ruoyi.collection.disruptor.event.ExamFileImportEvent;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/*
 *考试数据导入结束事件
 * */
@Slf4j
public class ExamFileImportEndHandler
    implements EventHandler<ExamFileImportEvent>, WorkHandler<ExamFileImportEvent> {
  private CountDownLatch countDownLatch;

  public ExamFileImportEndHandler(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void onEvent(ExamFileImportEvent event) throws Exception {
    try {
      countDownLatch.countDown();
      event.getImportContext().completeTask();
      long cur = event.getImportContext().getCompleteNum();
      long total = event.getImportContext().getTaskTotalNum();
      log.info("总数/完成:" + total + "/" + cur);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onEvent(ExamFileImportEvent event, long sequence, boolean endOfBatch)
      throws Exception {
    this.onEvent(event);
  }
}
