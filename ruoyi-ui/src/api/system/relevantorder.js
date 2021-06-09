import request from '@/utils/request'

// 查询归口订单管理列表
export function listRelevantorder(query) {
  return request({
    url: '/system/relevantorder/list',
    method: 'get',
    params: query
  })
}

// 查询归口订单管理详细
export function getRelevantorder(id) {
  return request({
    url: '/system/relevantorder/' + id,
    method: 'get'
  })
}

// 新增归口订单管理
export function addRelevantorder(data) {
  return request({
    url: '/system/relevantorder',
    method: 'post',
    data: data
  })
}

// 修改归口订单管理
export function updateRelevantorder(data) {
  return request({
    url: '/system/relevantorder',
    method: 'put',
    data: data
  })
}

// 删除归口订单管理
export function delRelevantorder(id) {
  return request({
    url: '/system/relevantorder/' + id,
    method: 'delete'
  })
}

// 导出归口订单管理
export function exportRelevantorder(query) {
  return request({
    url: '/system/relevantorder/export',
    method: 'get',
    params: query
  })
}