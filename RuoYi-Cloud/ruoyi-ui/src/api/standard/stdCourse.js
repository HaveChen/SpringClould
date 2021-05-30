import request from '@/utils/request'

// 查询课程列表
export function listStdCourse(query) {
  return request({
    url: '/standard/stdCourse/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getStdCourse(courseId) {
  return request({
    url: '/standard/stdCourse/' + courseId,
    method: 'get'
  })
}

// 新增课程
export function addStdCourse(data) {
  return request({
    url: '/standard/stdCourse',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateStdCourse(data) {
  return request({
    url: '/standard/stdCourse',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delStdCourse(courseId) {
  return request({
    url: '/standard/stdCourse/' + courseId,
    method: 'delete'
  })
}

// 导出课程
export function exportStdCourse(query) {
  return request({
    url: '/standard/stdCourse/export',
    method: 'get',
    params: query
  })
}