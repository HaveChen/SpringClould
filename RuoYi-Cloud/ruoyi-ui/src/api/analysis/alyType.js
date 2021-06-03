import request from '@/utils/request'

// 查询 分析项目类型列表
export function listAlyType(query) {
  return request({
    url: '/analysis/alyType/list',
    method: 'get',
    params: query
  })
}

// 查询 分析项目类型详细
export function getAlyType(projectTypeId) {
  return request({
    url: '/analysis/alyType/' + projectTypeId,
    method: 'get'
  })
}

// 新增 分析项目类型
export function addAlyType(data) {
  return request({
    url: '/analysis/alyType',
    method: 'post',
    data: data
  })
}

// 修改 分析项目类型
export function updateAlyType(data) {
  return request({
    url: '/analysis/alyType',
    method: 'put',
    data: data
  })
}

// 删除 分析项目类型
export function delAlyType(projectTypeId) {
  return request({
    url: '/analysis/alyType/' + projectTypeId,
    method: 'delete'
  })
}

// 导出 分析项目类型
export function exportAlyType(query) {
  return request({
    url: '/analysis/alyType/export',
    method: 'get',
    params: query
  })
}