import request from '@/utils/request'

// 查询教师列表
export function listStdTeacher(query) {
  return request({
    url: '/standard/stdTeacher/list',
    method: 'get',
    params: query
  })
}

// 查询教师详细
export function getStdTeacher(teacherId) {
  return request({
    url: '/standard/stdTeacher/' + teacherId,
    method: 'get'
  })
}

// 新增教师
export function addStdTeacher(data) {
  return request({
    url: '/standard/stdTeacher',
    method: 'post',
    data: data
  })
}

// 修改教师
export function updateStdTeacher(data) {
  return request({
    url: '/standard/stdTeacher',
    method: 'put',
    data: data
  })
}

// 删除教师
export function delStdTeacher(teacherId) {
  return request({
    url: '/standard/stdTeacher/' + teacherId,
    method: 'delete'
  })
}

// 导出教师
export function exportStdTeacher(query) {
  return request({
    url: '/standard/stdTeacher/export',
    method: 'get',
    params: query
  })
}