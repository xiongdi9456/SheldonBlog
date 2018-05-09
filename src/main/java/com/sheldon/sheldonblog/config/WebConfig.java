package com.sheldon.sheldonblog.config;

import com.sheldon.sheldonblog.interceptor.MyAdminInterceptor;
import com.sheldon.sheldonblog.interceptor.MyFrontEndInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{



    /**
     * 拦截器 给所有的/admin模式添加拦截器，检测admin没有登录
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyAdminInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/adminlogin");
        registry.addInterceptor(new MyFrontEndInterceptor()).addPathPatterns("/user/**");
        super.addInterceptors(registry);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        //主页跳转
        registry.addViewController("/").setViewName("forward:/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:static/");
    }

}
