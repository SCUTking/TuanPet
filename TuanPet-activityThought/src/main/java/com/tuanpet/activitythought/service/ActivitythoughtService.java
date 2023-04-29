package com.tuanpet.activitythought.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.activitythought.entity.ActivitythoughtEntity;
import com.tuanpet.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;


import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-04-28 20:24:08
 */
public interface ActivitythoughtService extends IService<ActivitythoughtEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ActivitythoughtEntity getActivityThoughtById(Long id);

}

