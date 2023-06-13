package com.example.recommend.railway.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recommend.railway.entity.TrainNumber;
import com.example.recommend.railway.service.TrainNumberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.io.Serializable;
import java.util.List;

/**
 * 订单管理
 *
 * @author xiaozhiwei
 * @since 2023-05-10 08:41:11
 */
@RestController
@RequestMapping("trainNumber")
@Tag(name = "TrainNumberController控制器")
public class TrainNumberController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private TrainNumberService trainNumberService;


    /**
     * 分页查询所有数据
     *
     * @param trainNumber 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据")
    public R selectAll(@RequestParam(defaultValue = "1") @Parameter(description = "当前页") Long current,
                       @RequestParam(defaultValue = "10") @Parameter(description = "每页记录数") Long size,
                       @Parameter(description = "查询条件,直接把属性名作为请求参数") TrainNumber trainNumber) {
        Page<TrainNumber> page = new Page<>(current, size);
        return success(this.trainNumberService.page(page, new QueryWrapper<>(trainNumber)));
    }
	/**
     * 查询所有数据
     */
    @GetMapping("list")
    @Operation(summary = "分页查询所有数据")
        public R selectAll() {
        return success(this.trainNumberService.list());
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.trainNumberService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param trainNumber 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据")
    public R insert(@RequestBody TrainNumber trainNumber) {
        return success(this.trainNumberService.save(trainNumber));
    }

    /**
     * 修改数据
     *
     * @param trainNumber 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据")
    public R update(@RequestBody TrainNumber trainNumber) {
        return success(this.trainNumberService.updateById(trainNumber));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @Operation(summary = "删除数据")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.trainNumberService.removeByIds(idList));
    }
}

