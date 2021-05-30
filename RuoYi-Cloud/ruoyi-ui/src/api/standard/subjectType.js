import request from '@/utils/request'

// 查询科目类型列表
export function listSubjectType(query) {
  return request({
    url: '/standard/subjectType/list',
    method: 'get',
    params: query
  })
}

// 查询科目类型详细
export function getSubjectType(subTypeId) {
  return request({
    url: '/standard/subjectType/' + subTypeId,
    method: 'get'
  })
}

// 新增科目类型
export function addSubjectType(data) {
  return request({
    url: '/standard/subjectType',
    method: 'post',
    data: data
  })
}

// 修改科目类型
export function updateSubjectType(data) {
  return request({
    url: '/standard/subjectType',
    method: 'put',
    data: data
  })
}

// 删除科目类型
export function delSubjectType(subTypeId) {
  return request({
    url: '/standard/subjectType/' + subTypeId,
    method: 'delete'
  })
}

// 导出科目类型
export function exportSubjectType(query) {
  return request({
    url: '/standard/subjectType/export',
    method: 'get',
    params: query
  })
}