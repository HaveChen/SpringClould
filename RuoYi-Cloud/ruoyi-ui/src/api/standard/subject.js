import request from '@/utils/request'

// 查询科目管理列表
export function listSubject(query) {
  return request({
    url: '/standard/subject/list',
    method: 'get',
    params: query
  })
}

// 查询科目管理详细
export function getSubject(subjectId) {
  return request({
    url: '/standard/subject/' + subjectId,
    method: 'get'
  })
}

// 新增科目管理
export function addSubject(data) {
  return request({
    url: '/standard/subject',
    method: 'post',
    data: data
  })
}

// 修改科目管理
export function updateSubject(data) {
  return request({
    url: '/standard/subject',
    method: 'put',
    data: data
  })
}

// 删除科目管理
export function delSubject(subjectId) {
  return request({
    url: '/standard/subject/' + subjectId,
    method: 'delete'
  })
}

// 导出科目管理
export function exportSubject(query) {
  return request({
    url: '/standard/subject/export',
    method: 'get',
    params: query
  })
}