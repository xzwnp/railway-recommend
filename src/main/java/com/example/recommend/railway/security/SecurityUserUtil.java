package com.example.recommend.railway.security;

import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.example.recommend.railway.jwt.JwtEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * com.example.servicebase.shiro.utils
 *
 * @author xzwnp
 * 2023/3/15
 * 22:21
 */
@Slf4j
public class SecurityUserUtil {
    public static Integer getUserId() {
        JwtEntity user = getUser();
        return user.getUserId();
    }

    public static JwtEntity getUser() {
        try {
            JwtUserDetails userDetails = (JwtUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            return userDetails.getUser();
        } catch (Exception e) {
            throw new ApiException(ApiErrorCode.TOKEN_INVALID);
        }


    }
}
