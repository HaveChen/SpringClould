import request from '@/utils/request'

// 查询科目管理列表
export function listStdSubject(query) {
  return request({
    url: '/standard/stdSubject/list',
    method: 'get',
    params: query
  })
}

// 查询学段字典；科目类型
export function listSubjectType() {
  return request({
    url: '/standard/stdSubjectType/listFiltrateData',
    method: 'get'
  })
}

// 查询学段字典；科目分类
export function listSubjectClassify() {
  return request({
    url: '/standard/stdSubjectClassify/listFiltrateData',
    method: 'get'
  })
}

// 查询科目管理详细
export function getStdSubject(subjectId) {
  return request({
    url: '/standard/stdSubject/' + subjectId,
    method: 'get'
  })
}

// 新增科目管理
export function addStdSubject(data) {
  return request({
    url: '/standard/stdSubject',
    method: 'post',
    data: data
  })
}

// 修改科目管理
export function updateStdSubject(data) {
  return request({
    url: '/standard/stdSubject',
    method: 'put',
    data: data
  })
}

// 删除科目管理
export function delStdSubject(subjectId) {
  return request({
    url: '/standard/stdSubject/' + subjectId,
    method: 'delete'
  })
}

// 导出科目管理
export function exportStdSubject(query) {
  return request({
    url: '/standard/stdSubject/export',
    method: 'get',
    params: query
  })
}
