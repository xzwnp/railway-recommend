package com.example.recommend.railway.security;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 当用户没有权限访问时,交给该handler处理
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        List<String> roles = SecurityUserUtil.getUser().getRoles();
        log.info("已有权限:{}不满足要求", roles);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        objectMapper.writeValue(httpServletResponse.getWriter(), R.failed(ApiErrorCode.NO_PERMISSION));
    }
}