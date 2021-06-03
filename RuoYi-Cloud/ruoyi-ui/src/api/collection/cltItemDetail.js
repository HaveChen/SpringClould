import request from '@/utils/request'

// 查询细目信息采集列表
export function listCltItemDetail(query) {
  return request({
    url: '/collection/cltItemDetail/list',
    method: 'get',
    params: query
  })
}

// 查询细目信息采集详细
export function getCltItemDetail(itemId) {
  return request({
    url: '/collection/cltItemDetail/' + itemId,
    method: 'get'
  })
}

// 新增细目信息采集
export function addCltItemDetail(data) {
  return request({
    url: '/collection/cltItemDetail',
    method: 'post',
    data: data
  })
}

// 修改细目信息采集
export function updateCltItemDetail(data) {
  return request({
    url: '/collection/cltItemDetail',
    method: 'put',
    data: data
  })
}

// 删除细目信息采集
export function delCltItemDetail(itemId) {
  return request({
    url: '/collection/cltItemDetail/' + itemId,
    method: 'delete'
  })
}

// 导出细目信息采集
export function exportCltItemDetail(query) {
  return request({
    url: '/collection/cltItemDetail/export',
    method: 'get',
    params: query
  })
}

// 初始化试卷下拉数据
export function listTestpaper(examId) {
  return request({
    url: `/collection/cltTestpaper/getTestPaperList/${examId}`,
    method: 'get'
  })
}