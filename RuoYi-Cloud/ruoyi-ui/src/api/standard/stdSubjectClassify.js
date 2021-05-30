import request from '@/utils/request'

// 查询科目分类列表
export function listStdSubjectClassify(query) {
  return request({
    url: '/standard/stdSubjectClassify/list',
    method: 'get',
    params: query
  })
}

// 查询科目分类详细
export function getStdSubjectClassify(subjectClassifyId) {
  return request({
    url: '/standard/stdSubjectClassify/' + subjectClassifyId,
    method: 'get'
  })
}

// 新增科目分类
export function addStdSubjectClassify(data) {
  return request({
    url: '/standard/stdSubjectClassify',
    method: 'post',
    data: data
  })
}

// 修改科目分类
export function updateStdSubjectClassify(data) {
  return request({
    url: '/standard/stdSubjectClassify',
    method: 'put',
    data: data
  })
}

// 删除科目分类
export function delStdSubjectClassify(subjectClassifyId) {
  return request({
    url: '/standard/stdSubjectClassify/' + subjectClassifyId,
    method: 'delete'
  })
}

// 导出科目分类
export function exportStdSubjectClassify(query) {
  return request({
    url: '/standard/stdSubjectClassify/export',
    method: 'get',
    params: query
  })
}