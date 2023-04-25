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
export function getSort(sortId) {
  return request({
    url: '/uniapp/sort/' + parseStrEmpty(sortId),
    method: 'get'
  })
}
// 新增分类
export function addSort(data) {
  return request({
    url: '/uniapp/sort',
    method: 'post',
    data: data
  })
}
//修改分类
export function updateSort(data) {
  return request({
    url: '/uniapp/sort',
    method: 'put',
    data: data
  })
}
// 删除分类
export function deleteSort(sortId) {
  return request({
    url: '/uniapp/sort/' + sortId,
    method: 'delete'
  })
}
