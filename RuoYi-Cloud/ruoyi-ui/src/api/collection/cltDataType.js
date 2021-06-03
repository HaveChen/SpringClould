import request from '@/utils/request'

// 查询数据采集类型列表
export function listCltDataType(query) {
  return request({
    url: '/collection/cltDataType/list',
    method: 'get',
    params: query
  })
}

// 查询数据采集类型详细
export function getCltDataType(dataTypeId) {
  return request({
    url: '/collection/cltDataType/' + dataTypeId,
    method: 'get'
  })
}

// 新增数据采集类型
export function addCltDataType(data) {
  return request({
    url: '/collection/cltDataType',
    method: 'post',
    data: data
  })
}

// 修改数据采集类型
export function updateCltDataType(data) {
  return request({
    url: '/collection/cltDataType',
    method: 'put',
    data: data
  })
}

// 删除数据采集类型
export function delCltDataType(dataTypeId) {
  return request({
    url: '/collection/cltDataType/' + dataTypeId,
    method: 'delete'
  })
}

// 导出数据采集类型
export function exportCltDataType(query) {
  return request({
    url: '/collection/cltDataType/export',
    method: 'get',
    params: query
  })
}
