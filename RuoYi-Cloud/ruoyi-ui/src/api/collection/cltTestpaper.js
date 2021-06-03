import request from '@/utils/request'

// 查询试卷列表
export function listCltTestpaper(query) {
  return request({
    url: '/collection/cltTestpaper/list',
    method: 'get',
    params: query
  })
}

// 查询试卷详细
export function getCltTestpaper(testpaperId) {
  return request({
    url: '/collection/cltTestpaper/' + testpaperId,
    method: 'get'
  })
}

// 新增试卷
export function addCltTestpaper(data) {
  return request({
    url: '/collection/cltTestpaper',
    method: 'post',
    data: data
  })
}

// 修改试卷
export function updateCltTestpaper(data) {
  return request({
    url: '/collection/cltTestpaper',
    method: 'put',
    data: data
  })
}

// 删除试卷
export function delCltTestpaper(testpaperId) {
  return request({
    url: '/collection/cltTestpaper/' + testpaperId,
    method: 'delete'
  })
}

// 导出试卷
export function exportCltTestpaper(query) {
  return request({
    url: '/collection/cltTestpaper/export',
    method: 'get',
    params: query
  })
}
// 获取根据试卷Id获取试卷详情列表
export function getTestpaperDetailById(testpaperId) {
  return request({
    url: '/collection/cltTestpaperDetail/getTestpaperDetailList/' + testpaperId,
    method: 'get'
  })
}