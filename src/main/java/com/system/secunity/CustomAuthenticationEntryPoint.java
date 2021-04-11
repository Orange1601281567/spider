package com.system.secunity;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.alibaba.fastjson.JSON;
import com.system.model.ReturnT;

/**
 * 自定義token認證,失敗返回類
 * @author Ablett_Chen
 *
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
    	ReturnT<String> result=new ReturnT<String>(500,"token可能過期，請重新登陸！");
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}