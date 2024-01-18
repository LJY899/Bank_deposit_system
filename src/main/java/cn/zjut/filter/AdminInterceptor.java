package cn.zjut.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session
        HttpSession session = request.getSession();
        log.info("Interceptor preHandle method called");

        // 检查session中的username是否为admin
        Long username = (Long) session.getAttribute("employee");
        log.info("Username in Session: {}", username);
        if (username == null || username != 1) {
            // 如果不是admin，则拦截访问
            response.sendRedirect("/employee/login"); // 重定向到拒绝访问页面
            return false;
        }

        // 如果是admin，则放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 在处理器执行后调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 在整个请求完成后调用
    }
}
