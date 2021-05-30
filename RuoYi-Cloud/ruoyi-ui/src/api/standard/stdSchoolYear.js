import request from '@/utils/request'

// 查询学年列表
export function listStdSchoolYear(query) {
  return request({
    url: '/standard/stdSchoolYear/list',
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

// 查询学年详细
export function getStdSchoolYear(schoolYearId) {
  return request({
    url: '/standard/stdSchoolYear/' + schoolYearId,
    method: 'get'
  })
}

// 新增学年
export function addStdSchoolYear(data) {
  return request({
    url: '/standard/stdSchoolYear',
    method: 'post',
    data: data
  })
}

// 修改学年
export function updateStdSchoolYear(data) {
  return request({
    url: '/standard/stdSchoolYear',
    method: 'put',
    data: data
  })
}

// 删除学年
export function delStdSchoolYear(schoolYearId) {
  return request({
    url: '/standard/stdSchoolYear/' + schoolYearId,
    method: 'delete'
  })
}

// 导出学年
export function exportStdSchoolYear(query) {
  return request({
    url: '/standard/stdSchoolYear/export',
    method: 'get',
    params: query
  })
}
