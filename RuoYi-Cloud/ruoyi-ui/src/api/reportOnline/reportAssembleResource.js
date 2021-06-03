import request from '@/utils/request'

// 查询报表详情列表
export function listReportAssembleResource(query) {
  return request({
    url: '/reportOnline/reportAssembleResource/list',
    method: 'get',
    params: query
  })
}

// 查询报表详情详细
export function getReportAssembleResource(assembleId) {
  return request({
    url: '/reportOnline/reportAssembleResource/' + assembleId,
    method: 'get'
  })
}

// 新增报表详情
export function addReportAssembleResource(data) {
  debugger;
  alert(12);
  return request({
    url: '/reportOnline/reportAssembleResource',
    method: 'post',
    data: data
  })
}

// 修改报表详情
export function updateReportAssembleResource(data) {
  debugger;
  alert(121);
  return request({
    url: '/reportOnline/reportAssembleResource',
    method: 'put',
    data: data
  })
}

// 删除报表详情
export function delReportAssembleResource(assembleId) {
  return request({
    url: '/reportOnline/reportAssembleResource/' + assembleId,
    method: 'delete'
  })
}

// 导出报表详情
export function exportReportAssembleResource(query) {
  return request({
    url: '/reportOnline/reportAssembleResource/export',
    method: 'get',
    params: query
  })
}
