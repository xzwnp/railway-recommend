package com.example.recommend.railway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recommend.railway.entity.User;
import com.example.recommend.railway.entity.request.UserLoginRequest;
import com.example.recommend.railway.entity.request.UserRegisterRequest;
import com.example.recommend.railway.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.io.Serializable;
import java.util.List;

/**
 * 用户管理
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@RestController
@RequestMapping("user")
@Tag(name = "用户管理")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("login")
    @Operation(summary = "登录")
    public R<String> login(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        return success(userService.login(userLoginRequest));
    }

    /**
     * 注册
     */
    @PostMapping("register")
    @Operation(summary = "注册")
    public R<String> register(@RequestBody @Validated UserRegisterRequest request) {
        return success(userService.register(request));
    }

    /**
     * 分页查询所有数据
     *
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据")
    public R selectAll(@RequestParam(defaultValue = "1") @Parameter(description = "当前页") Long current,
                       @RequestParam(defaultValue = "10") @Parameter(description = "每页记录数") Long size,
                       @Parameter(description = "查询条件,直接把属性名作为请求参数") User user) {
        Page<User> page = new Page<>(current, size);
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据")
    public R insert(@RequestBody User user) {
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据")
    public R update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @Operation(summary = "删除数据")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userService.removeByIds(idList));
    }
}

