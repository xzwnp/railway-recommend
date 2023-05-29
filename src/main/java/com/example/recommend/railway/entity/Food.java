package com.example.recommend.railway.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Food)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "餐食信息表")
public class Food extends Model<Food> {
    @Schema(description = "${column.comment}")
    private Integer id;
    @Schema(description = "食物名称")
    private String name;
    @Schema(description = "食物描述")
    private String description;
    @Schema(description = "价格")
    private BigDecimal price;
    @Schema(description = "销量")
    private Integer sales;
    @Schema(description = "评分")
    private BigDecimal score;
    @Schema(description = "辣度(0:不辣 1:微辣 2:中辣 3:特辣 4:麻辣 5:酸辣)")
    private Integer spicy;

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

