package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.mapper.OrderItemMapper;
import com.example.recommend.railway.entity.OrderItem;
import com.example.recommend.railway.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * 订单项(OrderItem)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}

