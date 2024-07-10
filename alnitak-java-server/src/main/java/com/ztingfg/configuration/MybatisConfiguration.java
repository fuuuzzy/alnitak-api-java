package com.ztingfg.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@MapperScan("com.ztingfg.mappers")
public class MybatisConfiguration {

}
