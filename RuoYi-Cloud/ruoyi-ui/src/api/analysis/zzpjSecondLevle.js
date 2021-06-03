import request from '@/utils/request'

// 查询二级分析层级 列表
export function listZzpjSecondLevle(query) {
  return request({
    url: '/analysis/zzpjSecondLevle/list',
    method: 'get',
    params: query
  })
}

// 查询二级分析层级 详细
export function getZzpjSecondLevle(alySecondLevelId) {
  return request({
    url: '/analysis/zzpjSecondLevle/' + alySecondLevelId,
    method: 'get'
  })
}

// 新增二级分析层级 
export function addZzpjSecondLevle(data) {
  return request({
    url: '/analysis/zzpjSecondLevle',
    method: 'post',
    data: data
  })
}

// 修改二级分析层级 
export function updateZzpjSecondLevle(data) {
  return request({
    url: '/analysis/zzpjSecondLevle',
    method: 'put',
    data: data
  })
}

// 删除二级分析层级 
export function delZzpjSecondLevle(alySecondLevelId) {
  return request({
    url: '/analysis/zzpjSecondLevle/' + alySecondLevelId,
    method: 'delete'
  })
}

// 导出二级分析层级 
export function exportZzpjSecondLevle(query) {
  return request({
    url: '/analysis/zzpjSecondLevle/export',
    method: 'get',
    params: query
  })
}