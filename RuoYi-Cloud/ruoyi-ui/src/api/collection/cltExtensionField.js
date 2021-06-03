import request from '@/utils/request'

// 查询采集扩展维度列表
export function listCltExtensionField(query) {
  return request({
    url: '/collection/cltExtensionField/list',
    method: 'get',
    params: query
  })
}

// 查询采集扩展维度详细
export function getCltExtensionField(extensionId) {
  return request({
    url: '/collection/cltExtensionField/' + extensionId,
    method: 'get'
  })
}

// 新增采集扩展维度
export function addCltExtensionField(data) {
  return request({
    url: '/collection/cltExtensionField',
    method: 'post',
    data: data
  })
}

// 修改采集扩展维度
export function updateCltExtensionField(data) {
  return request({
    url: '/collection/cltExtensionField',
    method: 'put',
    data: data
  })
}

// 删除采集扩展维度
export function delCltExtensionField(extensionId) {
  return request({
    url: '/collection/cltExtensionField/' + extensionId,
    method: 'delete'
  })
}

// 导出采集扩展维度
export function exportCltExtensionField(query) {
  return request({
    url: '/collection/cltExtensionField/export',
    method: 'get',
    params: query
  })
}