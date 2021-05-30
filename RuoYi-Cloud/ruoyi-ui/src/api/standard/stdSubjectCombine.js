import request from '@/utils/request'

// 查询科目组合类型列表
export function listStdSubjectCombine(query) {
  return request({
    url: '/standard/stdSubjectCombine/list',
    method: 'get',
    params: query
  })
}

// 查询科目组合类型详细
export function getStdSubjectCombine(subjectCombineId) {
  return request({
    url: '/standard/stdSubjectCombine/' + subjectCombineId,
    method: 'get'
  })
}

// 新增科目组合类型
export function addStdSubjectCombine(data) {
  return request({
    url: '/standard/stdSubjectCombine',
    method: 'post',
    data: data
  })
}

// 修改科目组合类型
export function updateStdSubjectCombine(data) {
  return request({
    url: '/standard/stdSubjectCombine',
    method: 'put',
    data: data
  })
}

// 删除科目组合类型
export function delStdSubjectCombine(subjectCombineId) {
  return request({
    url: '/standard/stdSubjectCombine/' + subjectCombineId,
    method: 'delete'
  })
}

// 导出科目组合类型
export function exportStdSubjectCombine(query) {
  return request({
    url: '/standard/stdSubjectCombine/export',
    method: 'get',
    params: query
  })
}