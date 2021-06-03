import request from '@/utils/request'

// 查询数据采集类型列表
export function listCltDataField(query) {
  return request({
    url: '/collection/cltDataField/list',
    method: 'get',
    params: query
  })
}

// 查询数据采集类型详细
export function getCltDataField(dataFieldId) {
  return request({
    url: '/collection/cltDataField/' + dataFieldId,
    method: 'get'
  })
}

// 新增数据采集类型
export function addCltDataField(data) {
  return request({
    url: '/collection/cltDataField',
    method: 'post',
    data: data
  })
}

// 修改数据采集类型
export function updateCltDataField(data) {
  return request({
    url: '/collection/cltDataField',
    method: 'put',
    data: data
  })
}

// 删除数据采集类型
export function delCltDataField(dataFieldId) {
  return request({
    url: '/collection/cltDataField/' + dataFieldId,
    method: 'delete'
  })
}

// 导出数据采集类型
export function exportCltDataField(query) {
  return request({
    url: '/collection/cltDataField/export',
    method: 'get',
    params: query
  })
}
// 初始化数据类型下拉数据
export function chuCltDatafieldToExam(query) {
  return request({
    url: '/collection/cltDataType/listFiltrateData',
    method: 'get',
    params: query
  })
}