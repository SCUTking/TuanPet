package com.tuanpet.reminder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;

import com.tuanpet.reminder.dao.ReminderDao;
import com.tuanpet.reminder.entity.ReminderEntity;
import com.tuanpet.reminder.service.ReminderService;


@Service("reminderService")
public class ReminderServiceImpl extends ServiceImpl<ReminderDao, ReminderEntity> implements ReminderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReminderEntity> page = this.page(
                new Query<ReminderEntity>().getPage(params),
                new QueryWrapper<ReminderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ReminderEntity> listByUserId(int id) {
        LambdaQueryWrapper<ReminderEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReminderEntity::getUserId,id);
        List<ReminderEntity> list = list(queryWrapper);
        return list;
    }
}
