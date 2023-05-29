package com.example.recommend.railway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.recommend.railway.entity.Station;

/**
 * 站点信息表(Station)表数据库访问层
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Mapper
public interface StationMapper extends BaseMapper<Station> {

}

