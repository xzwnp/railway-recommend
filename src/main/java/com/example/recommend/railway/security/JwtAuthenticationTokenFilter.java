package com.example.recommend.railway.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.recommend.railway.jwt.JwtEntity;
import com.example.recommend.railway.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    //认证失败的处理器
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 仅根据token设置用户信息,无论是否有token,token是否有效,都应该放行(因为有的请求不需要登录)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //请求头的key为Authorization,value前缀为Bearer
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);
        //Token不存在直接放行(可能是来登录的,Token存在就要检验一下Token有效性了)
        if (!(StringUtils.isNotBlank(authHeader) && authHeader.startsWith(JwtUtil.TOKEN_PREFIX))) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String authToken = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
        //校验token 无效直接结束,让用户早点重新登录
        if (!JwtUtil.checkToken(authToken)) {
            authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, new AuthenticationException(null) {
                @Override
                public String getMessage() {
                    return String.format("Token{%s}过期或无效", authToken);
                }
            });
            return;
        }

        JwtEntity jwtEntity = JwtUtil.getUserInfo(authToken);
        JwtUserDetails userDetails = new JwtUserDetails(jwtEntity);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        //关键 设置认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //方向
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}