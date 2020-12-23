package cn.zhanx.ke.cheng.config;

import cn.zhanx.ke.cheng.interceptor.CorsInterceptor;
import cn.zhanx.ke.cheng.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截全部路径，这个跨域的需要放在最上面
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/*/*/**");
                //.excludePathPatterns("/api/user/login","/api/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
