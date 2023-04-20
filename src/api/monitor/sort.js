import request from '@/utils/request'
import {parseStrEmpty} from "@/utils/ruoyi";

// 查询垃圾类别列表
export function list(query) {
  return request({
    url: '/uniapp/sort/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: '/system/user/' + parseStrEmpty(userId),
    method: 'get'
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}
