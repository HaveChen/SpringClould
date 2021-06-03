import request from '@/utils/request'

// 查询报表管理列表
export function listReportAssemble(query) {
  return request({
    url: '/reportOnline/reportAssemble/list',
    method: 'get',
    params: query
  })
}

// 查询报表管理详细
export function getReportAssemble(assembleId) {
  return request({
    url: '/reportOnline/reportAssemble/' + assembleId,
    method: 'get'
  })
}

// 新增报表管理
export function addReportAssemble(data) {
  return request({
    url: '/reportOnline/reportAssemble',
    method: 'post',
    data: data
  })
}

// 修改报表管理
export function updateReportAssemble(data) {
  return request({
    url: '/reportOnline/reportAssemble',
    method: 'put',
    data: data
  })
}

// 删除报表管理
export function delReportAssemble(assembleId) {
  return request({
    url: '/reportOnline/reportAssemble/' + assembleId,
    method: 'delete'
  })
}

// 导出报表管理
export function exportReportAssemble(query) {
  return request({
    url: '/reportOnline/reportAssemble/export',
    method: 'get',
    params: query
  })
}