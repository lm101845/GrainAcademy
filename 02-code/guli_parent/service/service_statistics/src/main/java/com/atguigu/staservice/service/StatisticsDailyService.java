package com.atguigu.staservice.service;

import com.atguigu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author liming
 * @since 2023-06-20
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    void createStatisticsByDay(String day);
}
