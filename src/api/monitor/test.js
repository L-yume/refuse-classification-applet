import request from '@/utils/request'

export function listTest(query) {
  return request({
    url: '/uniapp/test/list',
    method: 'get',
    params: query
  })
}
