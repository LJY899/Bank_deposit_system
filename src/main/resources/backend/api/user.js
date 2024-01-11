// 获取用户列表的函数
function getUserList(params) {
    return $axios({
        url: '/user/page',
        method: 'get',
        params
    });
}

// 启用或禁用用户的函数
function enableOrDisableUser(params) {
    return $axios({
        url: '/user',
        method: 'put',
        data: { ...params }
    });
}

// 添加新用户的函数
function addUser(params) {
    return $axios({
        url: '/user',
        method: 'post',
        data: { ...params }
    });
}

// 编辑用户信息的函数
function editUser(params) {
    return $axios({
        url: '/user',
        method: 'put',
        data: { ...params }
    });
}

// 根据用户ID查询用户详情的函数
function queryUserById(id) {
    return $axios({
        url: `/user/${id}`,
        method: 'get'
    });
}
