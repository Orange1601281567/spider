package com.system.secunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	 @Autowired
    private AuthenticationProvider provider;  //注入我们自己的AuthenticationProvider
	 
	 @Autowired
	 private JWTAuthorizationFilter jWTAuthorizationFilter;
	 
	//登录成功handler
	 @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    //登录失败handler
    @Autowired
    private LoginFailureHandler loginFailureHandler;
	
	//認證及授權
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
         * 指定用户认证时，默认从哪里获取认证用户信息
         */
		auth.authenticationProvider(provider);
	}
	
	
	//鑒權
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.cors().and().csrf().disable()
		 .formLogin().loginPage("/login")
		 .loginProcessingUrl("/login")
		 .failureForwardUrl("/views/login.html")  //登录失败返回url
		 .successHandler(loginSuccessHandler)//成功登录处理器
         .failureHandler(loginFailureHandler)//失败登录处理器
         // 测试用资源，需要验证了的用户才能访问
         .and()
         .authorizeRequests()
         .antMatchers( "/login" ).permitAll()//允许访问匹配的路径
         .antMatchers( "/doc.html" ).permitAll()//允许访问匹配的路径
         .antMatchers( "/swagger/**" ).permitAll()//允许访问匹配的路径
         .antMatchers( "/webjars/**" ).permitAll()//允许访问匹配的路径
//         .antMatchers( "/sysMenu/menu" ).permitAll()//允许访问匹配的路径
         .antMatchers( "/swagger-resources/**" ).permitAll()//允许访问匹配的路径
         .antMatchers( "/v2/**" ).permitAll()//允许访问匹配的路径
//         .antMatchers( "/sysUser/**" ).hasAuthority("admin")//需要擁有的角色
         .anyRequest().authenticated()//以上配置除外，其它需要认证
         .and()
         .addFilterBefore(jWTAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//         .addFilter(new JWTAuthorizationFilter(authenticationManager()))
         //.addF(new JWTAuthorizationFilter())
         // 不需要session
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .exceptionHandling()
         .accessDeniedHandler(new MyAccessDeniedHandler())
		 .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		 ;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
	
	@Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
	
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
	
}


