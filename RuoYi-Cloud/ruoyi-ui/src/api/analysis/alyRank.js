import request from '@/utils/request'

// 查询分析层级列表
export function listAlyRank(query) {
  return request({
    url: '/analysis/alyRank/list',
    method: 'get',
    params: query
  })
}

// 查询分析层级详细
export function getAlyRank(alyRankId) {
  return request({
    url: '/analysis/alyRank/' + alyRankId,
    method: 'get'
  })
}

// 新增分析层级
export function addAlyRank(data) {
  return request({
    url: '/analysis/alyRank',
    method: 'post',
    data: data
  })
}

// 修改分析层级
export function updateAlyRank(data) {
  return request({
    url: '/analysis/alyRank',
    method: 'put',
    data: data
  })
}

// 删除分析层级
export function delAlyRank(alyRankId) {
  return request({
    url: '/analysis/alyRank/' + alyRankId,
    method: 'delete'
  })
}

// 导出分析层级
export function exportAlyRank(query) {
  return request({
    url: '/analysis/alyRank/export',
    method: 'get',
    params: query
  })
}