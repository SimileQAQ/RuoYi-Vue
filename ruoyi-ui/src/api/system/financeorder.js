import request from '@/utils/request'

// 查询财务订单管理列表
export function listFinanceorder(query) {
  return request({
    url: '/system/financeorder/list',
    method: 'get',
    params: query
  })
}

// 查询财务订单管理详细
export function getFinanceorder(id) {
  return request({
    url: '/system/financeorder/' + id,
    method: 'get'
  })
}

// 新增财务订单管理
export function addFinanceorder(data) {
  return request({
    url: '/system/financeorder',
    method: 'post',
    data: data
  })
}

// 修改财务订单管理
export function updateFinanceorder(data) {
  return request({
    url: '/system/financeorder',
    method: 'put',
    data: data
  })
}

// 删除财务订单管理
export function delFinanceorder(id) {
  return request({
    url: '/system/financeorder/' + id,
    method: 'delete'
  })
}

// 导出财务订单管理
export function exportFinanceorder(query) {
  return request({
    url: '/system/financeorder/export',
    method: 'get',
    params: query
  })
}