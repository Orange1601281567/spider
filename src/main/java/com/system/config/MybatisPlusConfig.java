package com.system.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {
 
    /**
     * mybatis-plus分页插件<br>
     */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
	    return new PaginationInterceptor();
	}
}