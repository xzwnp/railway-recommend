package com.example.recommend.railway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recommend.railway.mapper.StationMapper;
import com.example.recommend.railway.entity.Station;
import com.example.recommend.railway.service.StationService;
import org.springframework.stereotype.Service;

/**
 * 站点信息表(Station)表服务实现类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Service("stationService")
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

}

