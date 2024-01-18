package cn.zjut.service.impl;

import cn.zjut.entity.BankCard;
import cn.zjut.mapper.BankCardMapper;
import cn.zjut.service.BankCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BankCardServicempl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {
}
