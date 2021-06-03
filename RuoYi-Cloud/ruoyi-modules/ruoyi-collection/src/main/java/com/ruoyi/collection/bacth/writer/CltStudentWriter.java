package com.ruoyi.collection.bacth.writer;

import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.domain.CltStudentDetail;
import com.ruoyi.collection.service.ICltProgressService;
import com.ruoyi.collection.service.ICltStudentDetailService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据入库。
 */
@Slf4j
public class CltStudentWriter implements ItemWriter<CltStudentDetail> {

  @Autowired
  private ICltStudentDetailService cltStudentDetailService;
  @Autowired
  private ICltProgressService cltProgressService;
  private CltProgress cltProgress;

  private CltStudentWriter() {
  }

  public CltStudentWriter(CltProgress cltProgress) {
    this.cltProgress = cltProgress;
  }

  @Override
  public void write(List<? extends CltStudentDetail> cltStudentDetails) throws Exception {
    // 过滤null值
    List<CltStudentDetail> newStudents =
        cltStudentDetails.stream().filter(s -> s != null).collect(Collectors.toList());
    cltStudentDetailService.saveBatch(newStudents);
    int successSize = newStudents.size();
    int errorSize = cltStudentDetails.size() - newStudents.size();
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
