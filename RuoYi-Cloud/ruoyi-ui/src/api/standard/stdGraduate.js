import request from '@/utils/request'

// 查询届别列表
export function listStdGraduate(query) {
  return request({
    url: '/standard/stdGraduate/list',
    method: 'get',
    params: query
  })
}

// 查询学段字典
export function listFiltrateData() {
  return request({
    url: '/standard/stdStudyLevel/listFiltrateData',
    method: 'get'
  })
}

// 查询届别详细
export function getStdGraduate(graduateId) {
  return request({
    url: '/standard/stdGraduate/' + graduateId,
    method: 'get'
  })
}

// 新增届别
export function addStdGraduate(data) {
  return request({
    url: '/standard/stdGraduate',
    method: 'post',
    data: data
  })
}

// 修改届别
export function updateStdGraduate(data) {
  return request({
    url: '/standard/stdGraduate',
    method: 'put',
    data: data
  })
}

// 删除届别
export function delStdGraduate(graduateId) {
  return request({
    url: '/standard/stdGraduate/' + graduateId,
    method: 'delete'
  })
}

// 导出届别
export function exportStdGraduate(query) {
  return request({
    url: '/standard/stdGraduate/export',
    method: 'get',
    params: query
  })
}
