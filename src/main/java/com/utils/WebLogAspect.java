package com.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.mapper.SysLogMapper;
import com.system.model.Log;
import com.system.model.SysLog;
import com.system.model.SysUser;
import com.system.secunity.JwtUtils;
import com.system.service.ISysUserService;
/**
 * 日志記錄工具類，記錄用戶的操作信息
 * @author Ablett_Chen
 *
 */
@Aspect
@Component
public class WebLogAspect {
		@Autowired
	    private SysLogMapper sysLogMapper;
		
//		@Autowired
//		private RedisTemplate redisTemplate;
		
		@Resource
	    public ISysUserService iSysUserService;
		
	    /**
	     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
	     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
	     */
	    @Pointcut("@annotation(com.system.model.Log)")
	    public void operationLog(){}
	 
	 
	    /**
	     * 环绕增强，相当于MethodInterceptor
	     */
	    @Around("operationLog()")
	    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
	        Object res = null;
	        long time = System.currentTimeMillis();
	        try {
	            res =  joinPoint.proceed();
	            time = System.currentTimeMillis() - time;
	            return res;
	        } finally {
	            try {
	                //方法执行完成后增加日志
	                addOperationLog(joinPoint,res,time);
	            }catch (Exception e){
	                System.out.println("LogAspect 操作失败：" + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	 
	   private void addOperationLog(JoinPoint joinPoint, Object res, long time){
		   HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   String tokenHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
		   String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
		   String username=JwtUtils.getUsername(token);
	        // 解密获得username，用于和数据库进行对比
		    QueryWrapper<SysUser> queryWrapper=new QueryWrapper<SysUser>();
		    queryWrapper.eq("username", username);
	        SysUser systemUser =iSysUserService.getOne(queryWrapper);
	        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
	        SysLog operationLog = new SysLog();
	        //获取内网地址IpUtils.intranetIp()
	        //获取外网地址IpUtils.internetIp()
	        operationLog.setIp(IpUtil.getIpAddr(request));
	        String argument="";
	        for(int i=0;i<joinPoint.getArgs().length;i++) {
	        	argument=argument+joinPoint.getArgs()[i].toString();
	        }
	        operationLog.setArgument(argument);
	        operationLog.setCreatetime(new Date());
	        operationLog.setWay(signature.getDeclaringTypeName() + "." + signature.getName());
	        operationLog.setUserid(systemUser.getUsername());
	        operationLog.setName(systemUser.getName());
	        Log annotation = signature.getMethod().getAnnotation(Log.class);
	        if(annotation != null){
	            operationLog.setDescription(getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),joinPoint.getArgs(),annotation));
	            operationLog.setOperatetype(annotation.operationType().getValue());
	            operationLog.setOperation(annotation.operationUnit().getValue());
	        }
	        sysLogMapper.insert(operationLog);
	    }
	 
	    /**
	     * 对当前登录用户和占位符处理
	     * @param argNames 方法参数名称数组
	     * @param args 方法参数数组
	     * @param annotation 注解信息
	     * @return 返回处理后的描述
	     */
	    private String getDetail(String[] argNames, Object[] args, Log annotation){
	        Map<Object, Object> map = new HashMap<>(4);
	        for(int i = 0;i < argNames.length;i++){
	            map.put(argNames[i],args[i]);
	        }
	 
	        String detail = annotation.detail();
	        try {
	            detail = annotation.detail();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return detail;
	    }
	 
	    @Before("operationLog()")
	    public void doBeforeAdvice(JoinPoint joinPoint){
	    }
	 
	    /**
	     * 处理完请求，返回内容
	     * @param ret
	     */
	    @AfterReturning(returning = "ret", pointcut = "operationLog()")
	    public void doAfterReturning(Object ret) {
	    }
	 
	    /**
	     * 后置异常通知
	     */
	    @AfterThrowing("operationLog()")
	    public void throwss(JoinPoint jp){
	    }
	 
	 
	    /**
	     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	     */
	    @After("operationLog()")
	    public void after(JoinPoint jp){
	    }
	}