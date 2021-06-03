import request from '@/utils/request'

// 查询考试类型列表
export function listCltExamType(query) {
  return request({
    url: '/collection/cltExamType/list',
    method: 'get',
    params: query
  })
}

// 查询考试类型详细
export function getCltExamType(examTypeId) {
  return request({
    url: '/collection/cltExamType/' + examTypeId,
    method: 'get'
  })
}

// 新增考试类型
export function addCltExamType(data) {
  return request({
    url: '/collection/cltExamType',
    method: 'post',
    data: data
  })
}

// 修改考试类型
export function updateCltExamType(data) {
  return request({
    url: '/collection/cltExamType',
    method: 'put',
    data: data
  })
}

// 删除考试类型
export function delCltExamType(examTypeId) {
  return request({
    url: '/collection/cltExamType/' + examTypeId,
    method: 'delete'
  })
}

// 导出考试类型
export function exportCltExamType(query) {
  return request({
    url: '/collection/cltExamType/export',
    method: 'get',
    params: query
  })
}