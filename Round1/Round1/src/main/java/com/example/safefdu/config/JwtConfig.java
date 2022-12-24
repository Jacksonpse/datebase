package com.example.safefdu.config;



import com.example.safefdu.utils.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtConfig implements WebMvcConfigurer{
    // addInterceptors 方法：添加拦截器
    // 参数：InterceptorRegistry 拦截器注册类
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                //.excludePathPatterns("/users/login","/users/checkcode"); // 不需要拦截的请求 不需要判断 token
                .excludePathPatterns("/**");
    }

    // 注入 Springboot 容器
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
