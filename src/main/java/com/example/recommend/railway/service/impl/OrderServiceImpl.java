package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.mapper.OrderMapper;
import com.example.recommend.railway.entity.Order;
import com.example.recommend.railway.service.OrderItemService;
import com.example.recommend.railway.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单信息表(Order)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    OrderItemService orderItemService;

    @Override
    @Transactional
    public void createOrder(Order order) {
        this.save(order);
        order.getOrderItems().forEach(item -> {
            item.setOrderId(order.getId());
        });
        orderItemService.saveBatch(order.getOrderItems());
    }
}

