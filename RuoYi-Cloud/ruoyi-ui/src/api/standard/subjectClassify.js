import request from '@/utils/request'

// 查询科目分类列表
export function listSubjectClassify(query) {
  return request({
    url: '/standard/subjectClassify/list',
    method: 'get',
    params: query
  })
}

// 查询科目分类详细
export function getSubjectClassify(subClassifyId) {
  return request({
    url: '/standard/subjectClassify/' + subClassifyId,
    method: 'get'
  })
}

// 新增科目分类
export function addSubjectClassify(data) {
  return request({
    url: '/standard/subjectClassify',
    method: 'post',
    data: data
  })
}

// 修改科目分类
export function updateSubjectClassify(data) {
  return request({
    url: '/standard/subjectClassify',
    method: 'put',
    data: data
  })
}

// 删除科目分类
export function delSubjectClassify(subClassifyId) {
  return request({
    url: '/standard/subjectClassify/' + subClassifyId,
    method: 'delete'
  })
}

// 导出科目分类
export function exportSubjectClassify(query) {
  return request({
    url: '/standard/subjectClassify/export',
    method: 'get',
    params: query
  })
}