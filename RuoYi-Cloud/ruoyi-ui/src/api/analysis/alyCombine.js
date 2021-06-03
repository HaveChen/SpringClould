import request from '@/utils/request'

// 查询分析选考组合列表
export function listAlyCombine(query) {
  return request({
    url: '/analysis/alyCombine/list',
    method: 'get',
    params: query
  })
}

// 查询分析选考组合详细
export function getAlyCombine(subjectCombineId) {
  return request({
    url: '/analysis/alyCombine/' + subjectCombineId,
    method: 'get'
  })
}

// 新增分析选考组合
export function addAlyCombine(data) {
  return request({
    url: '/analysis/alyCombine',
    method: 'post',
    data: data
  })
}

// 修改分析选考组合
export function updateAlyCombine(data) {
  return request({
    url: '/analysis/alyCombine',
    method: 'put',
    data: data
  })
}

// 删除分析选考组合
export function delAlyCombine(subjectCombineId) {
  return request({
    url: '/analysis/alyCombine/' + subjectCombineId,
    method: 'delete'
  })
}

// 导出分析选考组合
export function exportAlyCombine(query) {
  return request({
    url: '/analysis/alyCombine/export',
    method: 'get',
    params: query
  })
}