import request from '@/utils/request'

// 查询图明细列表
export function listReportChartDetail(query) {
  return request({
    url: '/reportOnline/reportChartDetail/list',
    method: 'get',
    params: query
  })
}

// 查询图明细详细
export function getReportChartDetail(chartDetailId) {
  return request({
    url: '/reportOnline/reportChartDetail/' + chartDetailId,
    method: 'get'
  })
}

// 新增图明细
export function addReportChartDetail(data) {
  return request({
    url: '/reportOnline/reportChartDetail',
    method: 'post',
    data: data
  })
}

// 修改图明细
export function updateReportChartDetail(data) {
  return request({
    url: '/reportOnline/reportChartDetail',
    method: 'put',
    data: data
  })
}

// 删除图明细
export function delReportChartDetail(chartDetailId) {
  return request({
    url: '/reportOnline/reportChartDetail/' + chartDetailId,
    method: 'delete'
  })
}

// 导出图明细
export function exportReportChartDetail(query) {
  return request({
    url: '/reportOnline/reportChartDetail/export',
    method: 'get',
    params: query
  })
}