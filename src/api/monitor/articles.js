import request from '@/utils/request'

// 查询文章分页列表的方法
export function getArticlePagedList(params) {
  return request({
    url: `/uniapp/article/list2?_=${Math.random()}`,
    method: 'get',
    params
  })
}

// 根据id查询文章详情的方法
export function getArticleById(params) {
  return request({
    url: `/uniapp/article/getById`,
    method: 'get',
    params
  })
}

// 删除文章方法
export function deleteArticle(params) {
  return request({
    url: `/uniapp/article/delete`,
    method: 'get',
    params
  })
}

// 添加文章保存方法
export function createArticle(data) {
  return request({
    url: '/uniapp/article',
    method: 'post',
    data
  })
}

// 更新文章保存方法
export function updateArticle(data) {
  return request({
    url: '/uniapp/article',
    method: 'put',
    data
  })
}

// 查询文章标签方法
export function getTagList(params) {
  return request({
    url: `/postTag/queryPageable?_=${Math.random()}`,
    method: 'get',
    params
  })
}

// 对接后台上传接口的方法
export function mdEditorUploadImage(data) {
  return request({
    url: '/ossController/upload',
    method: 'post',
    data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 绑定文章到栏目上
export function bindArticleToColumns(data) {
  return request({
    url: '/posts/insertPostTermTaxonomy',
    method: 'post',
    data
  })
}

// 文章置顶方法
export function setArticleOnTop(data) {
  return request({
    url: '/posts/setOnTop',
    method: 'post',
    data
  })
}

// 取消文章置顶方法
export function cancelArticleOnTop(data) {
  return request({
    url: '/posts/cancelOnTop',
    method: 'post',
    data
  })
}

export const uploadUrl = process.env.VUE_APP_BASE_API + '/ossController/upload/img'

export const importMdUrl = process.env.VUE_APP_BASE_API + '/posts/uploadMd'
