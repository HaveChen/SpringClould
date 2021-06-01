import request from '@/utils/request'

// 查询租户明细列表
export function listTenantDetail(query) {
  return request({
    url: '/system/tenantDetail/list',
    method: 'get',
    params: query
  })
}

// 查询租户明细详细
export function getTenantDetail(tenantInfoDetailId) {
  return request({
    url: '/system/tenantDetail/' + tenantInfoDetailId,
    method: 'get'
  })
}

// 新增租户明细
export function addTenantDetail(data) {
  return request({
    url: '/system/tenantDetail',
    method: 'post',
    data: data
  })
}

// 修改租户明细
export function updateTenantDetail(data) {
  return request({
    url: '/system/tenantDetail',
    method: 'put',
    data: data
  })
}

// 删除租户明细
export function delTenantDetail(tenantInfoDetailId) {
  return request({
    url: '/system/tenantDetail/' + tenantInfoDetailId,
    method: 'delete'
  })
}
