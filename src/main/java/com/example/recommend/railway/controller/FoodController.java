package com.example.recommend.railway.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recommend.railway.entity.Food;
import com.example.recommend.railway.service.FoodService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.Serializable;
import java.util.List;

/**
 * (Food)表控制层
 *
 * @author xiaozhiwei
 * @since 2023-04-26 09:38:31
 */
@RestController
@RequestMapping("food")
@Tag(name = "餐食管理")
public class FoodController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private FoodService foodService;

    /**
     * 分页查询所有数据
     *
     * @param food 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据")
        public R<Page<Food>> selectAll(@RequestParam(defaultValue = "1") @Parameter(description = "当前页") Long current,
                           @RequestParam(defaultValue = "10") @Parameter(description = "每页记录数") Long size,
                           @Parameter(description = "查询条件,可选") Food food) {
        Page<Food> page = new Page<>(current, size);                   
        return success(this.foodService.page(page, new QueryWrapper<>(food)));
    }
	/**
     * 查询所有数据
     */
    @GetMapping("list")
    @Operation(summary = "分页查询所有数据")
        public R selectAll(@Parameter(description = "查询条件,可选") Food food) {
        return success(this.foodService.list(new QueryWrapper<>(food)));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据")
    public R<Food> selectOne(@PathVariable Serializable id) {
        return success(this.foodService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param food 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据")
    public R insert(@RequestBody Food food) {
        return success(this.foodService.save(food));
    }

    /**
     * 修改数据
     *
     * @param food 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据")
    public R update(@RequestBody Food food) {
        return success(this.foodService.updateById(food));
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
        return success(this.foodService.removeByIds(idList));
    }
}

