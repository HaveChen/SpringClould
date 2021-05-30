import request from '@/utils/request'

// 查询年级组长列表
export function listStdGradeLeader(query) {
  return request({
    url: '/standard/stdGradeLeader/list',
    method: 'get',
    params: query
  })
}

// 查询字段：教师
export function listTeacher() {
  return request({
    url: '/standard/stdTeacher/listFiltrateData',
    method: 'get'
  })
}

// 查询字段：届别
export function listGraduate() {
  return request({
    url: '/standard/stdGraduate/listFiltrateData',
    method: 'get'
  })
}

// 查询字段：学年
export function listSchoolYear() {
  return request({
    url: '/standard/stdSchoolYear/listFiltrateData',
    method: 'get'
  })
}

// 查询字段：年级
export function listGrade() {
  return request({
    url: '/standard/stdGrade/listFiltrateData',
    method: 'get'
  })
}

// 查询字段：学期
export function listTerm() {
  return request({
    url: '/standard/stdTerm/listFiltrateData',
    method: 'get'
  })
}

// 查询年级组长详细
export function getStdGradeLeader(gradeLeaderId) {
  return request({
    url: '/standard/stdGradeLeader/' + gradeLeaderId,
    method: 'get'
  })
}

// 新增年级组长
export function addStdGradeLeader(data) {
  return request({
    url: '/standard/stdGradeLeader',
    method: 'post',
    data: data
  })
}

// 修改年级组长
export function updateStdGradeLeader(data) {
  return request({
    url: '/standard/stdGradeLeader',
    method: 'put',
    data: data
  })
}

// 删除年级组长
export function delStdGradeLeader(gradeLeaderId) {
  return request({
    url: '/standard/stdGradeLeader/' + gradeLeaderId,
    method: 'delete'
  })
}

// 导出年级组长
export function exportStdGradeLeader(query) {
  return request({
    url: '/standard/stdGradeLeader/export',
    method: 'get',
    params: query
  })
}
