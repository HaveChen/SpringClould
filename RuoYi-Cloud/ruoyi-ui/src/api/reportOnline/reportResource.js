import request from '@/utils/request'

// 查询报表资源列表
export function listReportResource(query) {
  return request({
    url: '/reportOnline/reportResource/list',
    method: 'get',
    params: query
  })
}

// 查询报表资源详细
export function getReportResource(resourceId) {
  return request({
    url: '/reportOnline/reportResource/' + resourceId,
    method: 'get'
  })
}

// 新增报表资源
export function addReportResource(data) {
  return request({
    url: '/reportOnline/reportResource',
    method: 'post',
    data: data
  })
}

// 修改报表资源
export function updateReportResource(data) {
  return request({
    url: '/reportOnline/reportResource',
    method: 'put',
    data: data
  })
}

// 删除报表资源
export function delReportResource(resourceId) {
  return request({
    url: '/reportOnline/reportResource/' + resourceId,
    method: 'delete'
  })
}

// 导出报表资源
export function exportReportResource(query) {
  return request({
    url: '/reportOnline/reportResource/export',
    method: 'get',
    params: query
  })
}