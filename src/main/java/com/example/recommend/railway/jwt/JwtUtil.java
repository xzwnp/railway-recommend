package com.example.recommend.railway.jwt;

/**
 * com.example.commonutils
 *
 * @author xzwnp
 * 2022/3/23
 * 12:59
 * Steps：
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author
 */
@Slf4j
public class JwtUtil {
    //持续时间,单位毫秒
    private static final long EXPIRE = 24 * 60 * 60 * 1000;
    //秘钥,使用Keys.secretKeyFor(SignatureAlgorithm.HS256)生成
    public static final byte[] APP_SECRET = {30, -13, -58, -80, -95, -24, 32, -34, -2, -68, -37, -110, -60, 90, -8, 19, 87, 45, -28, 13, 28, -35, -18, -71, -27, 121, 7, 21, -128, 121, 51, 70};
    private static final String SUBJECT = "jwt-demo";
    private static final String USER_INFO_CLAIM = "user-info";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String createJwtToken(JwtEntity jwtEntity) {
        String JwtToken = Jwts.builder()
                //第一部分 JWT头
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //第二部分 payload(主体),也叫claim 包括了一些基本信息和自定义信息
                //主题
                .setSubject(SUBJECT)
                //发布日期
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //自定义信息,需要根据情况修改
                .claim(USER_INFO_CLAIM, jwtEntity)
                //设置签名(秘钥加密算法)
                .signWith(SignatureAlgorithm.HS256,APP_SECRET)
                .compact();

        return JwtUtil.TOKEN_PREFIX + JwtToken;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (!StringUtils.hasLength(jwtToken)) {
            return false;
        }
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(6, jwtToken.length() - 1);
        }
        try {
            Jwts.parserBuilder().setSigningKey(APP_SECRET).build().parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.info("token不存在,错误信息:{}", e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public static JwtEntity getUserInfo(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(APP_SECRET).build().parseClaimsJws(token);
        Map<String, Object> map = (Map<String, Object>) claimsJws.getBody().get(USER_INFO_CLAIM);
        try {
            String s = objectMapper.writeValueAsString(map);
            return objectMapper.readValue(s, JwtEntity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("token解析失败!");
        }
    }
}
