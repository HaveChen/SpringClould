import request from '@/utils/request'

// 查询一级分析层级 列表
export function listZzpjFirstLevel(query) {
  return request({
    url: '/analysis/zzpjFirstLevel/list',
    method: 'get',
    params: query
  })
}

// 查询一级分析层级 详细
export function getZzpjFirstLevel(alyFirstLevelId) {
  return request({
    url: '/analysis/zzpjFirstLevel/' + alyFirstLevelId,
    method: 'get'
  })
}

// 新增一级分析层级 
export function addZzpjFirstLevel(data) {
  return request({
    url: '/analysis/zzpjFirstLevel',
    method: 'post',
    data: data
  })
}

// 修改一级分析层级 
export function updateZzpjFirstLevel(data) {
  return request({
    url: '/analysis/zzpjFirstLevel',
    method: 'put',
    data: data
  })
}

// 删除一级分析层级 
export function delZzpjFirstLevel(alyFirstLevelId) {
  return request({
    url: '/analysis/zzpjFirstLevel/' + alyFirstLevelId,
    method: 'delete'
  })
}

// 导出一级分析层级 
export function exportZzpjFirstLevel(query) {
  return request({
    url: '/analysis/zzpjFirstLevel/export',
    method: 'get',
    params: query
  })
}