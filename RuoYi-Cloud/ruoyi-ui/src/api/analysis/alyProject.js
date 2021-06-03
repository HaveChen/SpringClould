import request from '@/utils/request'

// 查询分析项目列表
export function listAlyProject(query) {
  return request({
    url: '/analysis/alyProject/list',
    method: 'get',
    params: query
  })
}

// 查询分析项目详细
export function getAlyProject(projectId) {
  return request({
    url: '/analysis/alyProject/' + projectId,
    method: 'get'
  })
}

// 新增分析项目
export function addAlyProject(data) {
  return request({
    url: '/analysis/alyProject',
    method: 'post',
    data: data
  })
}

// 修改分析项目
export function updateAlyProject(data) {
  return request({
    url: '/analysis/alyProject',
    method: 'put',
    data: data
  })
}

// 删除分析项目
export function delAlyProject(projectId) {
  return request({
    url: '/analysis/alyProject/' + projectId,
    method: 'delete'
  })
}

// 导出分析项目
export function exportAlyProject(query) {
  return request({
    url: '/analysis/alyProject/export',
    method: 'get',
    params: query
  })
}
// 初始化界别下拉数据
export function listGraduate() {
  return request({
    url: '/standard/stdGraduate/listFiltrateData',
    method: 'get'
  })
}
// 项目分析
export function projectAnalysis(projectId) {
  return request({
    url: '/analysis/alyProject/startAly/' + projectId,
    method: 'get'
  })
}
// 根据届别代码获取考试下拉
export function getExamDataById(graduateCode) {
  return request({
    url: '/collection/cltExamInfo/getExamList/' + graduateCode,
    method: 'get'
  })
}
// 获取分析进度条
export function getAnalysisProgress(projectId,actualVal ) {
  return request({
    url: `/analysis/alyProject/getProgressBar/${projectId}/${actualVal}`,
    method: 'get'
  })
}

