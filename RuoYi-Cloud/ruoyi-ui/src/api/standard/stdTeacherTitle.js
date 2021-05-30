import request from '@/utils/request'

// 查询教师职称列表
export function listStdTeacherTitle(query) {
  return request({
    url: '/standard/stdTeacherTitle/list',
    method: 'get',
    params: query
  })
}

// 查询教师职称详细
export function getStdTeacherTitle(teacherTitleId) {
  return request({
    url: '/standard/stdTeacherTitle/' + teacherTitleId,
    method: 'get'
  })
}

// 新增教师职称
export function addStdTeacherTitle(data) {
  return request({
    url: '/standard/stdTeacherTitle',
    method: 'post',
    data: data
  })
}

// 修改教师职称
export function updateStdTeacherTitle(data) {
  return request({
    url: '/standard/stdTeacherTitle',
    method: 'put',
    data: data
  })
}

// 删除教师职称
export function delStdTeacherTitle(teacherTitleId) {
  return request({
    url: '/standard/stdTeacherTitle/' + teacherTitleId,
    method: 'delete'
  })
}

// 导出教师职称
export function exportStdTeacherTitle(query) {
  return request({
    url: '/standard/stdTeacherTitle/export',
    method: 'get',
    params: query
  })
}