package com.example.recommend.railway.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * com.example.recommend.railway.entity.request
 *
 * @author xiaozhiwei
 * 2023/4/26
 * 11:23
 */
@Data
public class UserLoginRequest {
    @Schema(description = "手机号")
    @NotNull
    private String phone;
    @Schema(description = "密码")
    @NotNull
    private String password;
}
