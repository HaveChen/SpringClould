import request from '@/utils/request'

// 查询学生信息采集列表
export function listCltStudentDetail(query) {
  return request({
    url: '/collection/cltStudentDetail/list',
    method: 'get',
    params: query
  })
}

// 查询学生信息采集详细
export function getCltStudentDetail(studentId) {
  return request({
    url: '/collection/cltStudentDetail/' + studentId,
    method: 'get'
  })
}

// 新增学生信息采集
export function addCltStudentDetail(data) {
  return request({
    url: '/collection/cltStudentDetail',
    method: 'post',
    data: data
  })
}

// 修改学生信息采集
export function updateCltStudentDetail(data) {
  return request({
    url: '/collection/cltStudentDetail',
    method: 'put',
    data: data
  })
}

// 删除学生信息采集
export function delCltStudentDetail(studentId) {
  return request({
    url: '/collection/cltStudentDetail/' + studentId,
    method: 'delete'
  })
}

// 导出学生信息采集
export function exportCltStudentDetail(query) {
  return request({
    url: '/collection/cltStudentDetail/export',
    method: 'get',
    params: query
  })
}