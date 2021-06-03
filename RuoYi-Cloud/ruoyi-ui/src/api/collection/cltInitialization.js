import request from '@/utils/request'

// 初始化考试类型下拉数据
function listExaminationType(query) {
  return request({
    url: '/collection/cltExamType/listFiltrateData',
    method: 'get',
    params: query
  })
}
// 初始化考试模式下拉数据
function listExaminationMode(query) {
  return request({
    url: '/collection/cltExamMode/listFiltrateData',
    method: 'get',
    params: query
  })
}
// 初始化年级下拉数据
function listGrade() {
  return request({
    url: '/standard/stdGrade/listFiltrateData',
    method: 'get'
  })
}
// 初始化学年下拉数据
function listSchoolYear() {
  return request({
    url: '/standard/stdSchoolYear/listFiltrateData',
    method: 'get'
  })
}
// 初始化届别下拉数据
function listGraduate() {
  return request({
    url: '/standard/stdGraduate/listFiltrateData',
    method: 'get'
  })
}
// 初始化学期下拉数据
function listTerm() {
  return request({
    url: '/standard/stdTerm/listFiltrateData',
    method: 'get'
  })
}
export {
  listExaminationType,
  listExaminationMode,
  listGrade,
  listSchoolYear,
  listGraduate,
  listTerm,
}