import request from '@/utils/request'

export function listTest(query) {
  return request({
    url: '/uniapp/test/list',
    method: 'get',
    params: query
  })
}

export function addTest(data) {
  return request({
    url: '/uniapp/test',
    method: 'post',
    data: data
  })
}

export function updateTest(data) {
  return request({
    url: '/uniapp/test',
    method: 'put',
    data: data
  })
}

export function getTest(questionId) {
  return request({
    url: '/uniapp/test/' + questionId,
    method: 'get',
  })
}

// 删除试题
export function delTest(questionId) {
  return request({
    url: '/uniapp/test/' + questionId,
    method: 'delete'
  })
}
