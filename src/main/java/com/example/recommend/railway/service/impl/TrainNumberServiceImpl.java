package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.mapper.TrainNumberMapper;
import com.example.recommend.railway.entity.TrainNumber;
import com.example.recommend.railway.service.TrainNumberService;
import org.springframework.stereotype.Service;

/**
 * 车次信息表(TrainNumber)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-05-10 08:41:11
 */
@Service("trainNumberService")
public class TrainNumberServiceImpl extends ServiceImpl<TrainNumberMapper, TrainNumber> implements TrainNumberService {

}

