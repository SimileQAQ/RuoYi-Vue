import request from '@/utils/request'

// 查询资产管理处订单管理列表
export function listProcurementorder(query) {
  return request({
    url: '/system/procurementorder/list',
    method: 'get',
    params: query
  })
}

// 查询资产管理处订单管理详细
export function getProcurementorder(id) {
  return request({
    url: '/system/procurementorder/' + id,
    method: 'get'
  })
}

// 新增资产管理处订单管理
export function addProcurementorder(data) {
  return request({
    url: '/system/procurementorder',
    method: 'post',
    data: data
  })
}

// 修改资产管理处订单管理
export function updateProcurementorder(data) {
  return request({
    url: '/system/procurementorder',
    method: 'put',
    data: data
  })
}

// 删除资产管理处订单管理
export function delProcurementorder(id) {
  return request({
    url: '/system/procurementorder/' + id,
    method: 'delete'
  })
}

// 导出资产管理处订单管理
export function exportProcurementorder(query) {
  return request({
    url: '/system/procurementorder/export',
    method: 'get',
    params: query
  })
}