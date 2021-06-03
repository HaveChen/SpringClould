import request from '@/utils/request'

// 查询采集错误明细列表
export function listCltProgressDetail(query) {
  return request({
    url: '/collection/cltProgressDetail/list',
    method: 'get',
    params: query
  })
}

// 查询采集错误明细详细
export function getCltProgressDetail(progressDetailId) {
  return request({
    url: '/collection/cltProgressDetail/' + progressDetailId,
    method: 'get'
  })
}

// 新增采集错误明细
export function addCltProgressDetail(data) {
  return request({
    url: '/collection/cltProgressDetail',
    method: 'post',
    data: data
  })
}

// 修改采集错误明细
export function updateCltProgressDetail(data) {
  return request({
    url: '/collection/cltProgressDetail',
    method: 'put',
    data: data
  })
}

// 删除采集错误明细
export function delCltProgressDetail(progressDetailId) {
  return request({
    url: '/collection/cltProgressDetail/' + progressDetailId,
    method: 'delete'
  })
}

// 导出采集错误明细
export function exportCltProgressDetail(query) {
  return request({
    url: '/collection/cltProgressDetail/export',
    method: 'get',
    params: query
  })
}