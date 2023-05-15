package com.tuanpet.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.activity.entity.ActivityEntity;

import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-05-15 10:23:05
 */
public interface ActivityService extends IService<ActivityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

