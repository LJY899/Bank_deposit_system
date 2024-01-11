package cn.zjut.controller;
import cn.zjut.common.R;
import cn.zjut.entity.Employee;
import cn.zjut.entity.User;
import cn.zjut.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 新增员工
     * @param user
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody User user) {
        //设置默认密码，顺手加密了
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //账户默认状态1
        user.setStatus(1);
        //Mybatis-plus自动CRUD的功能，封装好了save方法
        userService.save(user);
        return R.success("新增员工成功");
    }
    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        if(name!=null)
            queryWrapper.like(User::getName,name);
        //执行查询
        userService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
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
    /**
     * 根据ID修改用户信息
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody User user){
        log.info(user.toString());

        userService.updateById(user);
        return R.success("用户信息修改成功");
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id){
        log.info("根据ID查询用户信息");
        User user = userService.getById(id);
        if(user != null) {
            return R.success(user);
        }
        return R.error("没有查询到用户信息");
    }
}
