import request from '@/utils/request'

export function listVideo(query) {
  return request({
    url: '/uniapp/video/list',
    method: 'get',
    params: query
  })
}
