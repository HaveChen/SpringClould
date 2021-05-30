import request from '@/utils/request'

// 查询学期列表
export function listStdTerm(query) {
  return request({
    url: '/standard/stdTerm/list',
    method: 'get',
    params: query
  })
}

// 查询学期详细
export function getStdTerm(termId) {
  return request({
    url: '/standard/stdTerm/' + termId,
    method: 'get'
  })
}

// 新增学期
export function addStdTerm(data) {
  return request({
    url: '/standard/stdTerm',
    method: 'post',
    data: data
  })
}

// 修改学期
export function updateStdTerm(data) {
  return request({
    url: '/standard/stdTerm',
    method: 'put',
    data: data
  })
}

// 删除学期
export function delStdTerm(termId) {
  return request({
    url: '/standard/stdTerm/' + termId,
    method: 'delete'
  })
}

// 导出学期
export function exportStdTerm(query) {
  return request({
    url: '/standard/stdTerm/export',
    method: 'get',
    params: query
  })
}