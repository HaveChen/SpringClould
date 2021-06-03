import request from '@/utils/request'

// 查询分析项目明细列表
export function listAlyDetail(query) {
  return request({
    url: '/analysis/alyDetail/list',
    method: 'get',
    params: query
  })
}

// 查询分析项目明细详细
export function getAlyDetail(projectDetailId) {
  return request({
    url: '/analysis/alyDetail/' + projectDetailId,
    method: 'get'
  })
}

// 新增分析项目明细
export function addAlyDetail(data) {
  return request({
    url: '/analysis/alyDetail',
    method: 'post',
    data: data
  })
}

// 修改分析项目明细
export function updateAlyDetail(data) {
  return request({
    url: '/analysis/alyDetail',
    method: 'put',
    data: data
  })
}

// 删除分析项目明细
export function delAlyDetail(projectDetailId) {
  return request({
    url: '/analysis/alyDetail/' + projectDetailId,
    method: 'delete'
  })
}

// 导出分析项目明细
export function exportAlyDetail(query) {
  return request({
    url: '/analysis/alyDetail/export',
    method: 'get',
    params: query
  })
}