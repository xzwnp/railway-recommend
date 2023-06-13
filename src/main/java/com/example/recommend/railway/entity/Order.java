package com.example.recommend.railway.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单信息表(Order)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "订单信息表")
@TableName("orders")
public class Order extends Model<Order> {
    @Schema(description = "${column.comment}")
    private Integer id;
    @Schema(description = "用户id")
    private Integer userId;
    @Schema(description = "总价格")
    private BigDecimal totalPrice;
    @Schema(description = "订单创建时间")
    private LocalDateTime createTime;
    @Schema(description = "订单状态(0:未支付,1:已支付,2:已送达,3:已结束)")
    private Integer status;
    @Schema(description = "车次编号")
    private Integer trainId;

    @TableField(exist = false)
    @Schema(description = "订单项")
    private List<OrderItem> orderItems;

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

