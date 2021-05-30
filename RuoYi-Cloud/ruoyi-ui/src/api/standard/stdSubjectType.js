import request from '@/utils/request'

// 查询科目类型列表
export function listStdSubjectType(query) {
  return request({
    url: '/standard/stdSubjectType/list',
    method: 'get',
    params: query
  })
}

// 查询科目类型详细
export function getStdSubjectType(subjectTypeId) {
  return request({
    url: '/standard/stdSubjectType/' + subjectTypeId,
    method: 'get'
  })
}

// 新增科目类型
export function addStdSubjectType(data) {
  return request({
    url: '/standard/stdSubjectType',
    method: 'post',
    data: data
  })
}

// 修改科目类型
export function updateStdSubjectType(data) {
  return request({
    url: '/standard/stdSubjectType',
    method: 'put',
    data: data
  })
}

// 删除科目类型
export function delStdSubjectType(subjectTypeId) {
  return request({
    url: '/standard/stdSubjectType/' + subjectTypeId,
    method: 'delete'
  })
}

// 导出科目类型
export function exportStdSubjectType(query) {
  return request({
    url: '/standard/stdSubjectType/export',
    method: 'get',
    params: query
  })
}