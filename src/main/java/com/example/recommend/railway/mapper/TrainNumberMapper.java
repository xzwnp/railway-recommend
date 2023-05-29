package com.example.recommend.railway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.recommend.railway.entity.TrainNumber;

/**
 * 车次信息表(TrainNumber)表数据库访问层
 *
 * @author xiaozhiwei
 * @since 2023-05-10 08:41:11
 */
@Mapper
public interface TrainNumberMapper extends BaseMapper<TrainNumber> {

}

