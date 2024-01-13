// 查询列表接口
const getproductPage = (params) => {
    return $axios({
        url: '/product/page',
        method: 'get',
        params
    })
}

// 查询详情
const queryproductById = (productId) => {
    return $axios({
        url: `/product/${productId}`,
        method: 'get'
    })
}

// 查产品列表的接口
const queryproductList = (params) => {
    return $axios({
        url: '/product/list',
        method: 'get',
        params
    })
}