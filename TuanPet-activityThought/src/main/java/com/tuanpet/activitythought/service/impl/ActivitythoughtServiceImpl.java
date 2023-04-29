package com.tuanpet.activitythought.service.impl;

import com.tuanpet.activitythought.dao.ActivitythoughtDao;
import com.tuanpet.activitythought.entity.ActivitythoughtEntity;
import com.tuanpet.activitythought.service.ActivitythoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;




@Service("activitythoughtService")
public class ActivitythoughtServiceImpl extends ServiceImpl<ActivitythoughtDao, ActivitythoughtEntity> implements ActivitythoughtService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ActivitythoughtEntity> page = this.page(
                new Query<ActivitythoughtEntity>().getPage(params),
                new QueryWrapper<ActivitythoughtEntity>()
        );

        return new PageUtils(page);
    }


    @Autowired
    private ActivitythoughtDao dao;
    @Override
    public ActivitythoughtEntity getActivityThoughtById(Long id) {
        ActivitythoughtEntity activitythoughtEntity = dao.selectById(id);

        return activitythoughtEntity;
    }
}
