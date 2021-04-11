package com.system.secunity;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token工具類
 * @author Ablett_Chen
 *
 */
public class JwtUtils {
	
	public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
	public static final long EXPIRITION = 60 * 30 ;
	public static final String APPSECRET_KEY = "congge_secret";
    

	
	/**
	 * 生成token
	 * @param username
	 * @param role
	 * @return
	 */
	public static String createToken(String username) {
		String token = Jwts
				.builder()
				.claim("username",username)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
		return token;
	}
	
	/**
	 * 获取用户名
	 * @param token
	 * @return
	 */
	public static String getUsername(String token){
    	Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    	return claims.get("username").toString();
    }
}