package cn.zjut.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 自定义元数据处理器
 * MP提供的自动填充功能，通过实现MetaObjectHandler(元数据处理器)，来实现服务
 * 为加入@TableField的注解提供自动填充的功能
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    HttpServletRequest httpServletRequest;
    /**
     * @param metaObject 插入时自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //填充创建时间
        metaObject.setValue("createTime", LocalDateTime.now());
        //填充 更新的时间
        metaObject.setValue("updateTime", LocalDateTime.now());
        //BaseContext工具类获取当前登陆人员信息
        //填充创建人信息
        metaObject.setValue("createUser", httpServletRequest.getSession().getAttribute("employee"));
        //填充更新人信息
        metaObject.setValue("updateUser", httpServletRequest.getSession().getAttribute("employee"));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //因为是更新，所以不用操作创建时间
        //更新的时间
        metaObject.setValue("updateTime", LocalDateTime.now());
        //更新人员
        metaObject.setValue("updateUser", httpServletRequest.getSession().getAttribute("employee"));
    }
}
