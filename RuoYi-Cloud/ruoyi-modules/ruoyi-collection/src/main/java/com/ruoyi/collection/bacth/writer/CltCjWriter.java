package com.ruoyi.collection.bacth.writer;

import com.ruoyi.collection.domain.CltCjDetail;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltCjDetailService;
import com.ruoyi.collection.service.ICltProgressService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据入库。
 */
@Slf4j
public class CltCjWriter implements ItemWriter<CltCjDetail> {

  @Autowired
  private ICltCjDetailService cltCjDetailService;
  @Autowired
  private ICltProgressService cltProgressService;
  private CltProgress cltProgress;

  private CltCjWriter() {
  }

  public CltCjWriter(CltProgress cltProgress) {
    this.cltProgress = cltProgress;
  }

  @Override
  public void write(List<? extends CltCjDetail> cltCjDetails) throws Exception {
    // 过滤null值
    List<CltCjDetail> newCjs =
        cltCjDetails.stream().filter(s -> s != null).collect(Collectors.toList());
    cltCjDetailService.saveBatch(newCjs);
    int successSize = newCjs.size();
    int errorSize = cltCjDetails.size() - newCjs.size();
    log.info("{}条数据入库成功", successSize);
    try {
      // 更新进度
      cltProgressService.updateImportLine(cltProgress.getProgressId(), successSize, errorSize);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("Failed to update import progress:{}", e.toString());
    }
  }
}
