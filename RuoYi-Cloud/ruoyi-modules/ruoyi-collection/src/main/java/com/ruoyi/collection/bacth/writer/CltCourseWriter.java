package com.ruoyi.collection.bacth.writer;

import com.ruoyi.collection.domain.CltCourse;
import com.ruoyi.collection.domain.CltProgress;
import com.ruoyi.collection.service.ICltCourseService;
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
public class CltCourseWriter implements ItemWriter<CltCourse> {

  @Autowired
  private ICltCourseService cltCourseService;
  @Autowired
  private ICltProgressService cltProgressService;
  private CltProgress cltProgress;

  private CltCourseWriter() {
  }

  public CltCourseWriter(CltProgress cltProgress) {
    this.cltProgress = cltProgress;
  }

  @Override
  public void write(List<? extends CltCourse> cltCourses) throws Exception {
    // 过滤null值
    List<CltCourse> newCjs =
        cltCourses.stream().filter(s -> s != null).collect(Collectors.toList());
    cltCourseService.saveBatch(newCjs);
    int successSize = newCjs.size();
    int errorSize = cltCourses.size() - newCjs.size();
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
