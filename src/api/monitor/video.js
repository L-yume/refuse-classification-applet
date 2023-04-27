import request from '@/utils/request'

export function listVideo(query) {
  return request({
    url: '/uniapp/video/list',
    method: 'get',
    params: query
  })
}

export function addVideo(data) {
  return request({
    url: '/uniapp/video',
    method: 'post',
    data: data
  })
}

// 删除视频
export function delVideo(videoIds) {
  return request({
    url: '/uniapp/video/' + videoIds,
    method: 'delete'
  })
}

export const uploadUrl = process.env.VUE_APP_BASE_API + '/ossController/upload/video'
