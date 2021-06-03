package com.ruoyi.collection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.collection.domain.CltExamInfo;
import com.ruoyi.collection.mapper.CltExamInfoMapper;
import com.ruoyi.collection.service.ICltExamInfoService;
import com.ruoyi.collection.service.ICltStudentDetailService;
import com.ruoyi.common.core.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试信息Service业务层处理
 *
 * @author chenyou
 * @date 2020-12-12
 */
@Service
public class CltExamInfoServiceImpl extends
    ServiceImpl<CltExamInfoMapper, CltExamInfo> implements ICltExamInfoService {

  @Autowired
  private CltExamInfoMapper cltExamInfoMapper;
  @Autowired
  private ICltStudentDetailService cltStudentDetailService;

  /**
   * 查询考试信息列表
   *
   * @param cltExamInfo 考试信息
   * @return 考试信息
   */
  @Override
  public List<CltExamInfo> selectCltExamInfoList(CltExamInfo cltExamInfo) {
    return cltExamInfoMapper.selectCltExamInfoList(cltExamInfo);
  }

  @Override
  @Transactional
  public boolean saveExamInfo(CltExamInfo cltExamInfo) {
    //TODO
//    if (cltExamInfo.getDeptId() != null) {
//      String deptCode = remoteSystemService.getDeptCodeById(cltExamInfo.getDeptId());
//      cltExamInfo.setDeptCode(deptCode);
//    }
    save(cltExamInfo);
    //保存采集管理信息
    cltExamInfoMapper.insertDataFieldToExam(cltExamInfo);
    return true;
  }


  @Override
  @Transactional
  public boolean removeExamsByIds(List<Long> asList) {
    //删除考试相关数据
    for (Long aLong : asList) {
      cltExamInfoMapper.cleanExamData(aLong);
    }
    return removeByIds(asList);
  }

  @Override
  public List<CltExamInfo> getExamByGraduateCode(String graduateCode) {
    QueryWrapper<CltExamInfo> q = new QueryWrapper<>();
    q.eq("graduate_code", graduateCode);
    return list(q);
  }

  @Override
  public void initExamIncludeDeptCode(CltExamInfo examInfo) {
    StringBuilder deptIncludeCode = new StringBuilder();
    String importSchoolCodes = cltStudentDetailService.getImportCodes(examInfo.getExamId() + "");
    if (StringUtils.isNotEmpty(importSchoolCodes)) {
      importSchoolCodes = "'" + importSchoolCodes.replaceAll(",", "','") + "'";
    }

    //获取考试最大组织和其下级组织
    //TODO
//    SysDept sysDept = remoteSystemService.getDeptAndChild(examInfo.getDeptCode());
//    if (sysDept != null) {
//      deptIncludeCode.append("'");
//      deptIncludeCode.append(sysDept.getDeptCode());
//      deptIncludeCode.append("',");
//      List<SysDept> childLists = sysDept.getChildren();
//      if (childLists != null && childLists.size() > 0) {
//        childLists = childLists.stream()
//            .filter(s -> !"school".equalsIgnoreCase(s.getDeptTypeCode()))
//            .collect(
//                Collectors.toList());
//        for (SysDept childList : childLists) {
//          deptIncludeCode.append("'");
//          deptIncludeCode.append(childList.getDeptCode());
//          deptIncludeCode.append("'");
//        }
//      }
//    }
    deptIncludeCode.append(importSchoolCodes);
    examInfo.setDeptAllCode(deptIncludeCode.toString());
  }

}

