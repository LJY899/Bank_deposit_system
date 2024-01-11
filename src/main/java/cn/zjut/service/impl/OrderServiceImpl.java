package cn.zjut.service.impl;

import cn.zjut.entity.Orders;
import cn.zjut.mapper.OrderMapper;
import cn.zjut.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    /**
     * 用户下单
     * @param orders
     */
    @Transactional
    public void submit(Orders orders){
        //获得当前用户id
        //Long userId = BaseContext.getCurrentId();
        //查询当前用户选择的产品id
        //LambdaQueryWrapper wrapper
        //向订单表插入数据，一条数据

        //向订单明细表？

        //
    }
}