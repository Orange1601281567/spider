 package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableCaching
@EnableSwagger2WebMvc
@MapperScan(basePackages = {"com.*.mapper"}) 
public class Application{
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ   启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
