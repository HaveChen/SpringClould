import request from '@/utils/request'

// 查询分析试卷列表
export function listAlyTestpaper(query) {
  return request({
    url: '/analysis/alyTestpaper/list',
    method: 'get',
    params: query
  })
}

// 查询分析试卷详细
export function getAlyTestpaper(alyTestpaperId) {
  return request({
    url: '/analysis/alyTestpaper/' + alyTestpaperId,
    method: 'get'
  })
}

// 新增分析试卷
export function addAlyTestpaper(data) {
  return request({
    url: '/analysis/alyTestpaper',
    method: 'post',
    data: data
  })
}

// 修改分析试卷
export function updateAlyTestpaper(data) {
  return request({
    url: '/analysis/alyTestpaper',
    method: 'put',
    data: data
  })
}

// 删除分析试卷
export function delAlyTestpaper(alyTestpaperId) {
  return request({
    url: '/analysis/alyTestpaper/' + alyTestpaperId,
    method: 'delete'
  })
}

// 导出分析试卷
export function exportAlyTestpaper(query) {
  return request({
    url: '/analysis/alyTestpaper/export',
    method: 'get',
    params: query
  })
}