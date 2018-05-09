package com.sheldon.sheldonblog.interceptor;

import com.sheldon.sheldonblog.consts.SessionConstants;
import com.sheldon.sheldonblog.entity.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MyAdminInterceptor implements HandlerInterceptor{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //检测有没有登录admin用户
        Object obj = httpServletRequest.getSession().getAttribute(SessionConstants.SESSION_ADMIN_CURRENT_USER);
        if (null == obj || !(obj instanceof AdminUser)) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/adminlogin");
            return false;
        }

        String ip = httpServletRequest.getRemoteAddr();
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("requestStartTime", startTime);
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        logger.debug("用户:"+ip+",访问目标:"+method.getDeclaringClass().getName() + "." + method.getName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        long startTime = (Long) httpServletRequest.getAttribute("requestStartTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        // 打印方法执行时间
        logger.debug("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : "
                    + executeTime + "ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
