package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.entity.request.UserRegisterRequest;
import com.example.recommend.railway.entity.request.UserLoginRequest;
import com.example.recommend.railway.jwt.JwtEntity;
import com.example.recommend.railway.jwt.JwtUtil;
import com.example.recommend.railway.mapper.UserMapper;
import com.example.recommend.railway.entity.User;
import com.example.recommend.railway.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * (User)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:32
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final String salt = "$2a$10$Q3Og2gyFbdfWvStJTfCdtu";

    @Override
    public String login(UserLoginRequest request) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, request.getPhone());
        User user = baseMapper.selectOne(wrapper);
        if (user == null) {
            throw new ApiException("账号不存在!");
        }
        String encodedPassword = DigestUtils.md5DigestAsHex((request.getPassword() + salt).getBytes());
        if (!user.getPassword().equals(encodedPassword)) {
            throw new ApiException("密码不正确!");
        }

        String token = createToken(user);
        return token;
    }

    @Override
    public String register(UserRegisterRequest request) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, request.getPhone());
        User user = baseMapper.selectOne(wrapper);
        if (user != null) {
            throw new ApiException("手机号已存在!");
        }
        user = new User();
        BeanUtils.copyProperties(request,user);
        baseMapper.insert(user);

        String token = createToken(user);
        return token;
    }

    private static String createToken(User user) {
        JwtEntity jwtEntity = new JwtEntity()
                .setUserId(user.getId())
                .setUsername(user.getPhone());
        String token = JwtUtil.createJwtToken(jwtEntity);
        return token;
    }
}

