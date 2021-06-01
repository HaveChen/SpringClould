import request from '@/utils/request'

// 查询多租户列表
export function listTenant(query) {
  return request({
    url: '/system/tenant/list',
    method: 'get',
    params: query
  })
}

// 查询多租户详细
export function getTenant(tenantInfoId) {
  return request({
    url: '/system/tenant/' + tenantInfoId,
    method: 'get'
  })
}

// 新增多租户
export function addTenant(data) {
  return request({
    url: '/system/tenant',
    method: 'post',
    data: data
  })
}

// 修改多租户
export function updateTenant(data) {
  return request({
    url: '/system/tenant',
    method: 'put',
    data: data
  })
}

// 删除多租户
export function delTenant(tenantInfoId) {
  return request({
    url: '/system/tenant/' + tenantInfoId,
    method: 'delete'
  })
}
