package com.example.recommend.railway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 站点信息表(Station)表实体类
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description="站点信息表")
public class Station extends Model<Station> {
    @Schema(description="${column.comment}")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Schema(description="站点名称")
    private Integer name;
    @Schema(description="描述")
    private Integer description;

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

