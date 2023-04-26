import request from '@/utils/request'

export function listVideo(query) {
  return request({
    url: '/uniapp/video/list',
    method: 'get',
    params: query
  })
}

export const uploadUrl = process.env.VUE_APP_BASE_API + '/ossController/upload/video'
