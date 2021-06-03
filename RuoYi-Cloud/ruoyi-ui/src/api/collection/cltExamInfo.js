import request from '@/utils/request'

// 查询考试信息列表
export function listCltExamInfo(query) {
  return request({
    url: '/collection/cltExamInfo/list',
    method: 'get',
    params: query
  })
}

// 查询考试信息详细
export function getCltExamInfo(examId) {
  return request({
    url: '/collection/cltExamInfo/' + examId,
    method: 'get'
  })
}

// 新增考试信息
export function addCltExamInfo(data) {
  return request({
    url: '/collection/cltExamInfo',
    method: 'post',
    data: data
  })
}

// 修改考试信息
export function updateCltExamInfo(data) {
  return request({
    url: '/collection/cltExamInfo',
    method: 'put',
    data: data
  })
}

// 删除考试信息
export function delCltExamInfo(examId) {
  return request({
    url: '/collection/cltExamInfo/' + examId,
    method: 'delete'
  })
}

// 导出考试信息
export function exportCltExamInfo(query) {
  return request({
    url: '/collection/cltExamInfo/export',
    method: 'get',
    params: query
  })

}

// 导出考试信息
export function studentImport(examId) {
  // debugger;
  return request({
    url: `/collection/importFile/importData/${examId}/bmk`,
    method: 'get'
  })
}

// 按照考试删除学生
export function studentDeleteByExamId(examId) {
  // debugger;
  return request({
    url: `/collection/cltStudentDetail/studentDelete/${examId}`,
    method: 'delete'
  })
}
// 按照考试删除试卷
export function papersDeleteByExamId(examId) {
  // debugger;
  return request({
    url: `/collection/cltTestpaper/testpaperDelete/${examId}`,
    method: 'delete'
  })
}
// 按照考试删除成绩
export function achievementDeleteByExamId(examId) {
  // debugger;
  return request({
    url: `/collection/cjDetail/cjDelete/${examId}`,
    method: 'delete'
  })
}
// 考试类型下拉数据
export function listExaminationType(query) {
  return request({
    url: '/collection/cltExamType/listFiltrateData',
    method: 'get',
    params: query
  })
}
// 考试模式下拉数据
export function listExaminationMode(query) {
  return request({
    url: '/collection/cltExamMode/listFiltrateData',
    method: 'get',
    params: query
  })
}
// 年级下拉数据
export function listGrade() {
  return request({
    url: '/standard/stdGrade/listFiltrateData',
    method: 'get'
  })
}
// 学年下拉数据
export function listSchoolYear() {
  return request({
    url: '/standard/stdSchoolYear/listFiltrateData',
    method: 'get'
  })
}
// 届别下拉数据
export function listGraduate() {
  return request({
    url: '/standard/stdGraduate/listFiltrateData',
    method: 'get'
  })
}
// 学期下拉数据
export function listTerm() {
  return request({
    url: '/standard/stdTerm/listFiltrateData',
    method: 'get'
  })
}
// 查询组织下拉树结构
export function treeselect() {
  return request({
    url: `/system/dept/treeselect`,
    method: 'get'
  })
}

