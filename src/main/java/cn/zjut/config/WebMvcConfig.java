package cn.zjut.config;

import cn.zjut.common.JacksonObjectMapper;
import cn.zjut.filter.AdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {
    //静态资源映射
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        log.info("开始进行静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
    /**
     * 拓展mvc框架转换器
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        //消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，java转json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器追加到mvc转换器容器
        converters.add(0,messageConverter);
    }
    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并设置拦截路径
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/user/**");
    }
}
