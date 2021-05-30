import request from '@/utils/request'

// 查询学段列表
export function listStdStudyLevel(query) {
  return request({
    url: '/standard/stdStudyLevel/list',
    method: 'get',
    params: query
  })
}

// 查询学段详细
export function getStdStudyLevel(studyLevelId) {
  return request({
    url: '/standard/stdStudyLevel/' + studyLevelId,
    method: 'get'
  })
}

// 新增学段
export function addStdStudyLevel(data) {
  return request({
    url: '/standard/stdStudyLevel',
    method: 'post',
    data: data
  })
}

// 修改学段
export function updateStdStudyLevel(data) {
  return request({
    url: '/standard/stdStudyLevel',
    method: 'put',
    data: data
  })
}

// 删除学段
export function delStdStudyLevel(studyLevelId) {
  return request({
    url: '/standard/stdStudyLevel/' + studyLevelId,
    method: 'delete'
  })
}

// 导出学段
export function exportStdStudyLevel(query) {
  return request({
    url: '/standard/stdStudyLevel/export',
    method: 'get',
    params: query
  })
}