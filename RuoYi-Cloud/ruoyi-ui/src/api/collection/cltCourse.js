import request from '@/utils/request'

// 查询采集课程列表
export function listCltCourse(query) {
  return request({
    url: '/collection/cltCourse/list',
    method: 'get',
    params: query
  })
}

// 查询采集课程详细
export function getCltCourse(courseId) {
  return request({
    url: '/collection/cltCourse/' + courseId,
    method: 'get'
  })
}

// 新增采集课程
export function addCltCourse(data) {
  return request({
    url: '/collection/cltCourse',
    method: 'post',
    data: data
  })
}

// 修改采集课程
export function updateCltCourse(data) {
  return request({
    url: '/collection/cltCourse',
    method: 'put',
    data: data
  })
}

// 删除采集课程
export function delCltCourse(courseId) {
  return request({
    url: '/collection/cltCourse/' + courseId,
    method: 'delete'
  })
}

// 导出采集课程
export function exportCltCourse(query) {
  return request({
    url: '/collection/cltCourse/export',
    method: 'get',
    params: query
  })
}