package cn.zjut.controller;

import cn.zjut.common.R;
import cn.zjut.entity.Employee;
import cn.zjut.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 员工信息前端控制器
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    ThreadLocal threadLocal = new ThreadLocal();

    @Autowired
    private EmployeeService employeeService;


    /**
     * 员工登出
     * @param request 删除request作用域中的session对象
     * @return 删除结果
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //尝试删除
        try {
            request.getSession().removeAttribute("employee");
        }catch (Exception e){
            //删除失败
            return R.error("登出失败");
        }
        return R.success("登出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee) {
        //设置默认密码，顺手加密了
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //账户默认状态1
        employee.setStatus(1);
        //获取当前新增操作人员的id
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        //Mybatis-plus自动CRUD的功能，封装好了save方法
        employeeService.save(employee);
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
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        if(name!=null)
            queryWrapper.like(Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    /**
     * 员工登录
     * @param request 如果登陆成功把对象放入Session中，方便后续拿取
     * @param employee 利用@RequestBody注解来解析前端传来的Json，同时用对象来封装
     * @return
     */
    @PostMapping("/login")
    R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        String username = employee.getUsername();
        log.info("登录");
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //通过用户名查这个员工对象
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,username);
        Employee emp = employeeService.getOne(queryWrapper);
        if (!emp.getUsername().equals(username)){
            return R.error("账户不存在");
            //密码是否正确
        }else if (!emp.getPassword().equals(password)){
            return R.error("账户密码错误");
            //员工账户状态是否正常，1：状态正常，0：封禁EmployeeController
        }else if (emp.getStatus()!=1){
            return R.error("当前账户正在封禁");
            //状态正常允许登陆
        }else {
            //登陆成功，将用户id存入Session并返回登录成功结果
            log.info("登陆成功，账户存入session");
            request.getSession().setAttribute("employee",emp.getId());
            //组长你写错了employee->emp
            return R.success(emp);
        }
    }

    /**
     * 根据ID修改员工信息
     * @return
     */
    @PutMapping
    @Transactional
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

        try {
            Long empId = (Long) request.getSession().getAttribute("employee");
            employee.setId(empId);  // 设置正确的员工ID
            employee.setUpdateTime(LocalDateTime.now());
            employee.setUpdateUser(empId);
//            employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
            employee.setCreateTime(LocalDateTime.now());
            employee.setUpdateTime(LocalDateTime.now());
            empId = (Long) request.getSession().getAttribute("employee");
            employee.setCreateUser(empId);
            employee.setUpdateUser(empId);
            employeeService.updateById(employee);
            // 返回完整的员工信息，包括更新后的员工ID
            R<Employee> successResponse = R.success(employee);

            // 将更新后的员工信息存储到 session 中
            request.getSession().setAttribute("employee", employee.getId());

            return R.success("员工信息修改成功");
        } catch (Exception e) {
            log.error("员工信息修改失败", e);
            return R.error("员工信息修改失败，请稍后重试");
        }
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据ID查询员工信息");
        Employee employee = employeeService.getById(id);
        if(employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到员工信息");
    }
}