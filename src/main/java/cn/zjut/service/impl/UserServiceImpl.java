package cn.zjut.service.impl;

import cn.zjut.entity.User;
import cn.zjut.mapper.UserMapper;
import cn.zjut.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
