package cn.zjut.controller;

import cn.zjut.common.R;
import cn.zjut.entity.Employee;
import cn.zjut.entity.User;
import cn.zjut.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 移动端用户登录
     * @param
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody Map map, HttpSession session){
        //获取手机号
        String phone = map.get("phone").toString();
        //获取密码
        String password = map.get("password").toString();
        //进行账号密码比对

        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //通过用户名查这个员工对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,phone);
        User user = userService.getOne(queryWrapper);
        if (user == null){
            return R.error("账户不存在");
            //密码是否正确
        }else if (!user.getPassword().equals(password)){
            return R.error("账户密码错误");
            //员工账户状态是否正常，1：状态正常，0：封禁
        }else if (user.getStatus()!=1){
            return R.error("当前账户正在封禁");
            //状态正常允许登陆
        }else {
            return R.success(user.toString());
        }
    }
}
