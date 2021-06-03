import request from '@/utils/request'

// 查询数据采集类型--考试列表
export function listCltDatafieldToExam(query) {
  return request({
    url: '/collection/cltDatafieldToExam/list',
    method: 'get',
    params: query
  })
}

// 查询数据采集类型--考试详细
export function getCltDatafieldToExam(examDataFieldId) {
  return request({
    url: '/collection/cltDatafieldToExam/' + examDataFieldId,
    method: 'get'
  })
}

// 新增数据采集类型--考试
export function addCltDatafieldToExam(data) {
  return request({
    url: '/collection/cltDatafieldToExam',
    method: 'post',
    data: data
  })
}

// 修改数据采集类型--考试
export function updateCltDatafieldToExam(data) {
  return request({
    url: '/collection/cltDatafieldToExam',
    method: 'put',
    data: data
  })
}

// 删除数据采集类型--考试
export function delCltDatafieldToExam(examDataFieldId) {
  return request({
    url: '/collection/cltDatafieldToExam/' + examDataFieldId,
    method: 'delete'
  })
}

// 导出数据采集类型--考试
export function exportCltDatafieldToExam(query) {
  return request({
    url: '/collection/cltDatafieldToExam/export',
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
