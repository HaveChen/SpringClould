import request from '@/utils/request'

// 查询表明细列表
export function listReportTableDetail(query) {
  return request({
    url: '/reportOnline/reportTableDetail/list',
    method: 'get',
    params: query
  })
}

// 查询表明细详细
export function getReportTableDetail(tableDetailId) {
  return request({
    url: '/reportOnline/reportTableDetail/' + tableDetailId,
    method: 'get'
  })
}

// 新增表明细
export function addReportTableDetail(data) {
  return request({
    url: '/reportOnline/reportTableDetail',
    method: 'post',
    data: data
  })
}

// 修改表明细
export function updateReportTableDetail(data) {
  return request({
    url: '/reportOnline/reportTableDetail',
    method: 'put',
    data: data
  })
}

// 删除表明细
export function delReportTableDetail(tableDetailId) {
  return request({
    url: '/reportOnline/reportTableDetail/' + tableDetailId,
    method: 'delete'
  })
}

// 导出表明细
export function exportReportTableDetail(query) {
  return request({
    url: '/reportOnline/reportTableDetail/export',
    method: 'get',
    params: query
  })
}