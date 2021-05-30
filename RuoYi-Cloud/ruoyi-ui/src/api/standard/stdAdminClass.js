import request from '@/utils/request'

// 查询行政班列表
export function listStdAdminClass(query) {
  return request({
    url: '/standard/stdAdminClass/list',
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

// 查询行政班详细
export function getStdAdminClass(classId) {
  return request({
    url: '/standard/stdAdminClass/' + classId,
    method: 'get'
  })
}

// 新增行政班
export function addStdAdminClass(data) {
  return request({
    url: '/standard/stdAdminClass',
    method: 'post',
    data: data
  })
}

// 修改行政班
export function updateStdAdminClass(data) {
  return request({
    url: '/standard/stdAdminClass',
    method: 'put',
    data: data
  })
}

// 删除行政班
export function delStdAdminClass(classId) {
  return request({
    url: '/standard/stdAdminClass/' + classId,
    method: 'delete'
  })
}

// 导出行政班
export function exportStdAdminClass(query) {
  return request({
    url: '/standard/stdAdminClass/export',
    method: 'get',
    params: query
  })
}
