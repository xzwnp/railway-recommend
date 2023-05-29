package com.example.recommend.railway.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * com.example.recommend.railway.controller
 *
 * @author xiaozhiwei
 * 2023/5/6
 * 11:34
 */
@Data
public class UserRegisterRequest {
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;
    @Schema(description = "辣度倾向(0-5)")
    private Integer spicyPreference;
}
