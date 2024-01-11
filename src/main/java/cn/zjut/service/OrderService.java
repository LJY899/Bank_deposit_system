package cn.zjut.service;

import cn.zjut.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.mockito.internal.matchers.Or;

public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
