import request from '@/utils/request'

// 查询指标维度名称库 列表
export function listReportLibrary(query) {
  return request({
    url: '/reportOnline/reportLibrary/list',
    method: 'get',
    params: query
  })
}

// 查询指标维度名称库 详细
export function getReportLibrary(libraryId) {
  return request({
    url: '/reportOnline/reportLibrary/' + libraryId,
    method: 'get'
  })
}

// 新增指标维度名称库 
export function addReportLibrary(data) {
  return request({
    url: '/reportOnline/reportLibrary',
    method: 'post',
    data: data
  })
}

// 修改指标维度名称库 
export function updateReportLibrary(data) {
  return request({
    url: '/reportOnline/reportLibrary',
    method: 'put',
    data: data
  })
}

// 删除指标维度名称库 
export function delReportLibrary(libraryId) {
  return request({
    url: '/reportOnline/reportLibrary/' + libraryId,
    method: 'delete'
  })
}

// 导出指标维度名称库 
export function exportReportLibrary(query) {
  return request({
    url: '/reportOnline/reportLibrary/export',
    method: 'get',
    params: query
  })
}