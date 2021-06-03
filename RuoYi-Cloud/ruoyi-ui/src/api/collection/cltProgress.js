import request from '@/utils/request'

// 查询采集进度列表
export function listCltProgress(query) {
  return request({
    url: '/collection/cltProgress/list',
    method: 'get',
    params: query
  })
}

// 查询采集进度详细
export function getCltProgress(progressId) {
  return request({
    url: '/collection/cltProgress/' + progressId,
    method: 'get'
  })
}

// 新增采集进度
export function addCltProgress(data) {
  return request({
    url: '/collection/cltProgress',
    method: 'post',
    data: data
  })
}

// 修改采集进度
export function updateCltProgress(data) {
  return request({
    url: '/collection/cltProgress',
    method: 'put',
    data: data
  })
}

// 删除采集进度
export function delCltProgress(progressId) {
  return request({
    url: '/collection/cltProgress/' + progressId,
    method: 'delete'
  })
}

// 导出采集进度
export function exportCltProgress(query) {
  return request({
    url: '/collection/cltProgress/export',
    method: 'get',
    params: query
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