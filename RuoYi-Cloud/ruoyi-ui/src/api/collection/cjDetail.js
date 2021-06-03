import request from '@/utils/request'

// 查询成绩数据采集列表
export function listCjDetail(query) {
  return request({
    url: '/collection/cjDetail/list',
    method: 'get',
    params: query
  })
}

// 查询成绩数据采集详细
export function getCjDetail(cjId) {
  return request({
    url: '/collection/cjDetail/' + cjId,
    method: 'get'
  })
}

// 新增成绩数据采集
export function addCjDetail(data) {
  return request({
    url: '/collection/cjDetail',
    method: 'post',
    data: data
  })
}

// 修改成绩数据采集
export function updateCjDetail(data) {
  return request({
    url: '/collection/cjDetail',
    method: 'put',
    data: data
  })
}

// 删除成绩数据采集
export function delCjDetail(cjId) {
  return request({
    url: '/collection/cjDetail/' + cjId,
    method: 'delete'
  })
}

// 导出成绩数据采集
export function exportCjDetail(query) {
  return request({
    url: '/collection/cjDetail/export',
    method: 'get',
    params: query
  })
}
// 初始化学校下拉数据
export function listSchool(examId) {
  return request({
    url: `/system/dept/getSchoolList/${examId}`,
    method: 'get'
  })
}
// 初始化试卷下拉数据
export function listTestpaper(examId) {
  return request({
    url: `/collection/cltTestpaper/getTestPaperList/${examId}`,
    method: 'get'
  })
}
// 初始化数据类型下拉数据
export function chuCltDatafieldToExam(query) {
  return request({
    url: '/collection/cltDataType/listFiltrateData',
    method: 'get',
    params: query
  })
}