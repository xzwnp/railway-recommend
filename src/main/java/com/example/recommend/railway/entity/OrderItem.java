package com.example.recommend.railway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单项(OrderItem)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "订单项")
public class OrderItem extends Model<OrderItem> {
    @Schema(description = "${column.comment}")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(description = "订单编号")
    private Integer orderId;
    @Schema(description = "餐食id")
    private Integer foodId;
    @Schema(description = "单价")
    private Object singlePrice;
    @Schema(description = "数量")
    private Integer number;

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

