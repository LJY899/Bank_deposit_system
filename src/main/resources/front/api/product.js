//查询所有订单
function productListApi() {
    return $axios({
        'url': '/product/list',
        'method': 'get',
    })
}

//分页查询订单
function productPagingApi(data) {
    return $axios({
        'url': '/product/page',
        'method': 'get',
        params:{...data}
    })
}