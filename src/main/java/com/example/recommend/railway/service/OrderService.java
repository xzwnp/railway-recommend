package com.example.recommend.railway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.recommend.railway.entity.Order;

/**
 * 订单信息表(Order)表服务接口
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
public interface OrderService extends IService<Order> {

    void createOrder(Order order);
}

