package com.example.recommend.railway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * com.example.recommend.railway
 *
 * @author xiaozhiwei
 * 2023/4/26
 * 8:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.recommend.railway", "com.baomidou.mybatisplus.extension"})
@MapperScan(basePackages = "com.example.recommend.railway.mapper")
public class RecommendApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecommendApplication.class, args);
    }
}
