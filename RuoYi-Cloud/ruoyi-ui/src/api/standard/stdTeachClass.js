import request from '@/utils/request'

// 查询教学班列表
export function listStdTeachClass(query) {
  return request({
    url: '/standard/stdTeachClass/list',
    method: 'get',
    params: query
  })
}

// 查询字段：班级类型
export function listClassType() {
  return request({
    url: '/standard/stdClassType/listFiltrateData',
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

// 查询教学班详细
export function getStdTeachClass(teachClassId) {
  return request({
    url: '/standard/stdTeachClass/' + teachClassId,
    method: 'get'
  })
}

// 新增教学班
export function addStdTeachClass(data) {
  return request({
    url: '/standard/stdTeachClass',
    method: 'post',
    data: data
  })
}

// 修改教学班
export function updateStdTeachClass(data) {
  return request({
    url: '/standard/stdTeachClass',
    method: 'put',
    data: data
  })
}

// 删除教学班
export function delStdTeachClass(teachClassId) {
  return request({
    url: '/standard/stdTeachClass/' + teachClassId,
    method: 'delete'
  })
}

// 导出教学班
export function exportStdTeachClass(query) {
  return request({
    url: '/standard/stdTeachClass/export',
    method: 'get',
    params: query
  })
}
