import request from '@/utils/request'

// 查询分析细目列表
export function listAlyItemDetail(query) {
  return request({
    url: '/analysis/alyItemDetail/list',
    method: 'get',
    params: query
  })
}

// 查询分析细目详细
export function getAlyItemDetail(alyItemId) {
  return request({
    url: '/analysis/alyItemDetail/' + alyItemId,
    method: 'get'
  })
}

// 新增分析细目
export function addAlyItemDetail(data) {
  return request({
    url: '/analysis/alyItemDetail',
    method: 'post',
    data: data
  })
}

// 修改分析细目
export function updateAlyItemDetail(data) {
  return request({
    url: '/analysis/alyItemDetail',
    method: 'put',
    data: data
  })
}

// 删除分析细目
export function delAlyItemDetail(alyItemId) {
  return request({
    url: '/analysis/alyItemDetail/' + alyItemId,
    method: 'delete'
  })
}

// 导出分析细目
export function exportAlyItemDetail(query) {
  return request({
    url: '/analysis/alyItemDetail/export',
    method: 'get',
    params: query
  })
}