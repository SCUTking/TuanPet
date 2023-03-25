package com.tuanpet.reminder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.reminder.entity.ReminderEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-20 22:09:26
 */
public interface ReminderService extends IService<ReminderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ReminderEntity> listByUserId(int id);
}

