import request from '@/utils/request'

// 查询采集扩展维度明细列表
export function listCltExtensionFieldDetail(query) {
  return request({
    url: '/collection/cltExtensionFieldDetail/list',
    method: 'get',
    params: query
  })
}

// 查询采集扩展维度明细详细
export function getCltExtensionFieldDetail(extensionDetailId) {
  return request({
    url: '/collection/cltExtensionFieldDetail/' + extensionDetailId,
    method: 'get'
  })
}

// 新增采集扩展维度明细
export function addCltExtensionFieldDetail(data) {
  return request({
    url: '/collection/cltExtensionFieldDetail',
    method: 'post',
    data: data
  })
}

// 修改采集扩展维度明细
export function updateCltExtensionFieldDetail(data) {
  return request({
    url: '/collection/cltExtensionFieldDetail',
    method: 'put',
    data: data
  })
}

// 删除采集扩展维度明细
export function delCltExtensionFieldDetail(extensionDetailId) {
  return request({
    url: '/collection/cltExtensionFieldDetail/' + extensionDetailId,
    method: 'delete'
  })
}

// 导出采集扩展维度明细
export function exportCltExtensionFieldDetail(query) {
  return request({
    url: '/collection/cltExtensionFieldDetail/export',
    method: 'get',
    params: query
  })
}