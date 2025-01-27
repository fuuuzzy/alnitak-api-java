package com.ztingfg.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ztingfg.comment.utils.JsonUtil;
import com.ztingfg.interceptors.AuthingInterceptor;
import com.ztingfg.interceptors.TraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration(proxyBeanMethods = false)
public class AlnitakWebConfiguration implements WebMvcConfigurer {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtil.getObjectMapper();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(new AuthingInterceptor())
                .excludePathPatterns("/api/v1/auth/**")
                .addPathPatterns("/api/v1/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 匹配所有的路径
                .allowCredentials(true)
                .allowedHeaders("*")   // 设置请求头
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 设置允许的方式
                .allowedOriginPatterns("*");
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() > 15 ?
                        Runtime.getRuntime().availableProcessors() / 4 : 2
                , new ThreadFactoryBuilder().setNameFormat("async-task-%d").build());
    }

    @Bean
    public Executor virtualThreadExecutor() {
        return Executors.newThreadPerTaskExecutor(Thread.ofVirtual()
                .name("virtual-t-", 1).factory());
    }
}
