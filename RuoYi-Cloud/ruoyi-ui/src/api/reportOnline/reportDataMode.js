import request from '@/utils/request'

// 查询数据模型列表
export function listReportDataMode(query) {
  return request({
    url: '/reportOnline/reportDataMode/list',
    method: 'get',
    params: query
  })
}

// 查询数据模型详细
export function getReportDataMode(dataModeId) {
  return request({
    url: '/reportOnline/reportDataMode/' + dataModeId,
    method: 'get'
  })
}

// 新增数据模型
export function addReportDataMode(data) {
  return request({
    url: '/reportOnline/reportDataMode',
    method: 'post',
    data: data
  })
}

// 修改数据模型
export function updateReportDataMode(data) {
  return request({
    url: '/reportOnline/reportDataMode',
    method: 'put',
    data: data
  })
}

// 删除数据模型
export function delReportDataMode(dataModeId) {
  return request({
    url: '/reportOnline/reportDataMode/' + dataModeId,
    method: 'delete'
  })
}

// 导出数据模型
export function exportReportDataMode(query) {
  return request({
    url: '/reportOnline/reportDataMode/export',
    method: 'get',
    params: query
  })
}