package cn.zjut.controller;

import cn.zjut.common.R;
import cn.zjut.entity.Employee;
import cn.zjut.entity.Orders;
import cn.zjut.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        if(name!=null)
            queryWrapper.like(Orders::getNumber,name);
        //添加排序条件
        queryWrapper.orderByDesc(Orders::getOrderTime);
        //执行查询
        orderService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}