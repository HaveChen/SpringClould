import request from '@/utils/request'

// 查询班级类型列表
export function listStdClassType(query) {
  return request({
    url: '/standard/stdClassType/list',
    method: 'get',
    params: query
  })
}

// 查询班级类型详细
export function getStdClassType(classTypeId) {
  return request({
    url: '/standard/stdClassType/' + classTypeId,
    method: 'get'
  })
}

// 新增班级类型
export function addStdClassType(data) {
  return request({
    url: '/standard/stdClassType',
    method: 'post',
    data: data
  })
}

// 修改班级类型
export function updateStdClassType(data) {
  return request({
    url: '/standard/stdClassType',
    method: 'put',
    data: data
  })
}

// 删除班级类型
export function delStdClassType(classTypeId) {
  return request({
    url: '/standard/stdClassType/' + classTypeId,
    method: 'delete'
  })
}

// 导出班级类型
export function exportStdClassType(query) {
  return request({
    url: '/standard/stdClassType/export',
    method: 'get',
    params: query
  })
}