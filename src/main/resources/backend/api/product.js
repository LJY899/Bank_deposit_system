// 查询列表接口
const getproductPage = (params) => {
  return $axios({
    url: '/product/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteProduct = (ids) => {
  return $axios({
    url: '/product',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editproduct = (params) => {
  return $axios({
    url: '/product',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addProduct = (params) => {
  return $axios({
    url: '/product',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情
const queryproductById = (productId) => {
  return $axios({
    url: `/product/${productId}`,
    method: 'get'
  })
}


// 查菜品列表的接口
const queryproductList = (params) => {
  return $axios({
    url: '/product/list',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售---批量起售停售接口
const productStatusByStatus = (params) => {
  return $axios({
    url: `/product/productStatus/${params.productStatus}`,
    method: 'post',
    params: { ids: params.productId }
  })
}