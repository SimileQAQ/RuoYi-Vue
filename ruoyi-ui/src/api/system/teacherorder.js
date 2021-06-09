import request from '@/utils/request'

// 查询教师订单管理列表
export function listTeacherorder(query) {
  return request({
    url: '/system/teacherorder/list',
    method: 'get',
    params: query
  })
}

// 查询教师订单管理详细
export function getTeacherorder(orderId) {
  return request({
    url: '/system/teacherorder/' + orderId,
    method: 'get'
  })
}

// 新增教师订单管理
export function addTeacherorder(data) {
  return request({
    url: '/system/teacherorder',
    method: 'post',
    data: data
  })
}

// 修改教师订单管理
export function updateTeacherorder(data) {
  return request({
    url: '/system/teacherorder',
    method: 'put',
    data: data
  })
}

// 删除教师订单管理
export function delTeacherorder(orderId) {
  return request({
    url: '/system/teacherorder/' + orderId,
    method: 'delete'
  })
}

// 导出教师订单管理
export function exportTeacherorder(query) {
  return request({
    url: '/system/teacherorder/export',
    method: 'get',
    params: query
  })
}

//查询教师 有的项目编号
export function getProjectid(query) {
  return request({
    url: '/system/teacherorder/export',
    method: 'get',
    params: query
  })
}

