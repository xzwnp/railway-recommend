package com.example.recommend.railway.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车次信息表(TrainNumber)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-05-10 08:41:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "车次信息表")
public class TrainNumber extends Model<TrainNumber> {
    @Schema(description = "${column.comment}")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(description = "车次编号")
    private String trainNumberId;
    @Schema(description = "出发城市")
    private String startStation;
    @Schema(description = "到达城市")
    private String endStation;
    @Schema(description = "发车日期")
    private LocalDate departureDate;
    @Schema(description = "发车时间")
    private LocalTime departureTime;

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

