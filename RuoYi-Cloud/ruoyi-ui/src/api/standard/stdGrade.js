import request from '@/utils/request'

// 查询年级列表
export function listStdGrade(query) {
  return request({
    url: '/standard/stdGrade/list',
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

// 查询年级详细
export function getStdGrade(gradeId) {
  return request({
    url: '/standard/stdGrade/' + gradeId,
    method: 'get'
  })
}

// 新增年级
export function addStdGrade(data) {
  return request({
    url: '/standard/stdGrade',
    method: 'post',
    data: data
  })
}

// 修改年级
export function updateStdGrade(data) {
  return request({
    url: '/standard/stdGrade',
    method: 'put',
    data: data
  })
}

// 删除年级
export function delStdGrade(gradeId) {
  return request({
    url: '/standard/stdGrade/' + gradeId,
    method: 'delete'
  })
}

// 导出年级
export function exportStdGrade(query) {
  return request({
    url: '/standard/stdGrade/export',
    method: 'get',
    params: query
  })
}
