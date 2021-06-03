import request from '@/utils/request'

// 查询分析组织列表
export function listAlyDept(query) {
  return request({
    url: '/analysis/alyDept/list',
    method: 'get',
    params: query
  })
}

// 查询分析组织详细
export function getAlyDept(alyDeptId) {
  return request({
    url: '/analysis/alyDept/' + alyDeptId,
    method: 'get'
  })
}

// 新增分析组织
export function addAlyDept(data) {
  return request({
    url: '/analysis/alyDept',
    method: 'post',
    data: data
  })
}

// 修改分析组织
export function updateAlyDept(data) {
  return request({
    url: '/analysis/alyDept',
    method: 'put',
    data: data
  })
}

// 删除分析组织
export function delAlyDept(alyDeptId) {
  return request({
    url: '/analysis/alyDept/' + alyDeptId,
    method: 'delete'
  })
}

// 导出分析组织
export function exportAlyDept(query) {
  return request({
    url: '/analysis/alyDept/export',
    method: 'get',
    params: query
  })
}