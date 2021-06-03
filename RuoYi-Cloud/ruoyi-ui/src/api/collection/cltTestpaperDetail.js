import request from '@/utils/request'

// 查询试卷详情列表
export function listCltTestpaperDetail(query) {
  return request({
    url: '/collection/cltTestpaperDetail/list',
    method: 'get',
    params: query
  })
}

// 查询试卷详情详细
export function getCltTestpaperDetail(testpaperDetailId) {
  return request({
    url: '/collection/cltTestpaperDetail/' + testpaperDetailId,
    method: 'get'
  })
}

// 新增试卷详情
export function addCltTestpaperDetail(data) {
  return request({
    url: '/collection/cltTestpaperDetail',
    method: 'post',
    data: data
  })
}

// 修改试卷详情
export function updateCltTestpaperDetail(data) {
  return request({
    url: '/collection/cltTestpaperDetail',
    method: 'put',
    data: data
  })
}

// 删除试卷详情
export function delCltTestpaperDetail(testpaperDetailId) {
  return request({
    url: '/collection/cltTestpaperDetail/' + testpaperDetailId,
    method: 'delete'
  })
}

// 导出试卷详情
export function exportCltTestpaperDetail(query) {
  return request({
    url: '/collection/cltTestpaperDetail/export',
    method: 'get',
    params: query
  })
}