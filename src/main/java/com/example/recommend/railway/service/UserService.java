package com.example.recommend.railway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.recommend.railway.entity.request.UserRegisterRequest;
import com.example.recommend.railway.entity.User;
import com.example.recommend.railway.entity.request.UserLoginRequest;

/**
 * (User)表服务接口
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:32
 */
public interface UserService extends IService<User> {

    String login(UserLoginRequest request);

    String register(UserRegisterRequest request);
}

