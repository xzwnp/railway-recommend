package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.mapper.FoodMapper;
import com.example.recommend.railway.entity.Food;
import com.example.recommend.railway.service.FoodService;
import org.springframework.stereotype.Service;

/**
 * (Food)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Service("foodService")
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

}

