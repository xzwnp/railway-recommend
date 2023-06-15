package com.example.recommend.railway.security;

import com.example.recommend.railway.jwt.JwtEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring security使用的Subject类,实现了UserDetails接口
 * 这里相当于使用适配器模式对JwtEntity进行适配,使其符合SpringSecurity的要求
 */
public class JwtUserDetails implements UserDetails {

    private final JwtEntity user;


    private final List<SimpleGrantedAuthority> permissionList;


    public JwtUserDetails(JwtEntity jwtEntity) {
        this.user = jwtEntity;
        if (!CollectionUtils.isEmpty(jwtEntity.getRoles())) {
            //角色需要ROLE_开头,权限不需要
            this.permissionList = jwtEntity.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } else {
            this.permissionList = new ArrayList<>();
        }
    }

    /**
     * 获取用户权限
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList;
    }

    public Integer getUserId() {
        return user.getUserId();
    }

    public JwtEntity getUser() {
        return user;
    }

    /**
     * 随便返回点什么,反正token认证不需要密码
     */
    @Override
    public String getPassword() {
        return user.getUsername();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期
     * todo
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * todo
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * todo
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     * todo
     **/
    @Override
    public boolean isEnabled() {
        return true;
    }
}