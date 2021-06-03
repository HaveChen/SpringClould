import request from '@/utils/request'

// 查询考试模式列表
export function listCltExamMode(query) {
  return request({
    url: '/collection/cltExamMode/list',
    method: 'get',
    params: query
  })
}

// 查询考试模式详细
export function getCltExamMode(examModeId) {
  return request({
    url: '/collection/cltExamMode/' + examModeId,
    method: 'get'
  })
}

// 新增考试模式
export function addCltExamMode(data) {
  return request({
    url: '/collection/cltExamMode',
    method: 'post',
    data: data
  })
}

// 修改考试模式
export function updateCltExamMode(data) {
  return request({
    url: '/collection/cltExamMode',
    method: 'put',
    data: data
  })
}

// 删除考试模式
export function delCltExamMode(examModeId) {
  return request({
    url: '/collection/cltExamMode/' + examModeId,
    method: 'delete'
  })
}

// 导出考试模式
export function exportCltExamMode(query) {
  return request({
    url: '/collection/cltExamMode/export',
    method: 'get',
    params: query
  })
}