import request from '@/utils/request'

// 查询分析试卷详情 列表
export function listAlyTestpaperDetail(query) {
  return request({
    url: '/analysis/alyTestpaperDetail/list',
    method: 'get',
    params: query
  })
}

// 查询分析试卷详情 详细
export function getAlyTestpaperDetail(alyTestpaperDetailId) {
  return request({
    url: '/analysis/alyTestpaperDetail/' + alyTestpaperDetailId,
    method: 'get'
  })
}

// 新增分析试卷详情 
export function addAlyTestpaperDetail(data) {
  return request({
    url: '/analysis/alyTestpaperDetail',
    method: 'post',
    data: data
  })
}

// 修改分析试卷详情 
export function updateAlyTestpaperDetail(data) {
  return request({
    url: '/analysis/alyTestpaperDetail',
    method: 'put',
    data: data
  })
}

// 删除分析试卷详情 
export function delAlyTestpaperDetail(alyTestpaperDetailId) {
  return request({
    url: '/analysis/alyTestpaperDetail/' + alyTestpaperDetailId,
    method: 'delete'
  })
}

// 导出分析试卷详情 
export function exportAlyTestpaperDetail(query) {
  return request({
    url: '/analysis/alyTestpaperDetail/export',
    method: 'get',
    params: query
  })
}