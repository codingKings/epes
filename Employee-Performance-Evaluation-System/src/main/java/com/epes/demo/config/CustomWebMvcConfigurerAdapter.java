package com.epes.demo.config;

/**
* @author 程龙
* @version 创建时间：2019年10月2日 
* @ClassName 类名称：
* @Description 类描述：
*/
import com.epes.demo.tool.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对来自所有的请求进行拦截
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("*");
    }
}
