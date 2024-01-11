package cn.zjut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zjut.entity.ShoppingCart;
import cn.zjut.mapper.ShoppingCartMapper;
import cn.zjut.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
