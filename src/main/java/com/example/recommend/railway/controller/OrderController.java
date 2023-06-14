package com.example.recommend.railway.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recommend.railway.entity.Order;
import com.example.recommend.railway.entity.OrderItem;
import com.example.recommend.railway.entity.request.OrderCreateRequest;
import com.example.recommend.railway.security.SecurityUserUtil;
import com.example.recommend.railway.service.OrderItemService;
import com.example.recommend.railway.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.io.Serializable;
import java.util.List;

/**
 * 订单管理
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@RestController
@RequestMapping("order")
@Tag(name = "订单管理")
public class OrderController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 分页查询所有数据
     *
     * @param order 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据")
    public R<Page<Order>> selectAll(@RequestParam(defaultValue = "1") @Parameter(description = "当前页") Long current,
                                    @RequestParam(defaultValue = "10") @Parameter(description = "每页记录数") Long size,
                                    @Parameter(description = "查询条件,直接把属性名作为请求参数") Order order) {
        Page<Order> page = new Page<>(current, size);
        return success(this.orderService.page(page, new QueryWrapper<>(order)));
    }

    /**
     * 查询所有数据
     */
    @GetMapping("list")
    @Operation(summary = "分页查询所有数据")
    public R selectAll(@Parameter(description = "查询条件,直接把属性名作为请求参数") Order orderQuery) {
        orderQuery.setUserId(SecurityUserUtil.getUserId());
        List<Order> orders = this.orderService.list(new QueryWrapper<>(orderQuery));
        orders.forEach(order -> order.setOrderItems(orderItemService.list(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, order.getId()))));
        return success(orders);
    }

    @PostMapping("create")
    @Operation(summary = "创建订单")
    public R<?> createOrder(@RequestBody Order order) {
        //设置用户id
        order.setUserId(SecurityUserUtil.getUserId());
        order.setId(null);
        //创建订单
        orderService.createOrder(order);
        //创建订单项
        order.getOrderItems().forEach(item -> {
            item.setId(null);
            item.setOrderId(order.getId());
        });
        orderItemService.saveBatch(order.getOrderItems());
        return R.ok(null);
    }


    /**
     * 修改数据
     *
     * @param orderQuery 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据")
    public R update(@RequestBody Order orderQuery) {
        orderQuery.setUserId(SecurityUserUtil.getUserId());
        return success(this.orderService.updateById(orderQuery));
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
        return success(this.orderService.getById(id));
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
        return success(this.orderService.removeByIds(idList));
    }
}

