package com.example.recommend.railway.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * (User)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description="")
public class User extends Model<User> {
    @Schema(description="${column.comment}")
    private Integer id;
    @Schema(description="手机号")
    private String phone;
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;
    @Schema(description="辣度倾向(0-5)")
    private Integer spicyPreference;
    @Schema(description="汤食倾向")
    private Integer soupPreference;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    }

