// 根据电话号码查询银行卡详情的函数
function queryBankCardByPhoneNumber(phoneNumber) {
    return $axios({
        url: `/bankcard/byPhoneNumber`,
        method: 'get',
        params: {
            phoneNumber: phoneNumber
        }
    });
}


async function updateBalance(params) {
    try {
        const response = await $axios({
            url: '/bankcard/updateBalance',
            method: 'post',
            data: params
        });

        console.log('更新余额成功', response);
        // 处理更新成功后的逻辑
    } catch (error) {
        console.error('更新余额时出错', error);
        // 处理错误...
        throw error; // 抛出异常，让调用方处理错误
    }
}