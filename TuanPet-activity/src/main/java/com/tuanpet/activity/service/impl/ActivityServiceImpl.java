package com.tuanpet.activity.service.impl;

import com.tuanpet.activity.service.ActivityService;
import com.tuanpet.activity.dao.ActivityDao;
import com.tuanpet.activity.entity.ActivityEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;




@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, ActivityEntity> implements ActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ActivityEntity> page = this.page(
                new Query<ActivityEntity>().getPage(params),
                new QueryWrapper<ActivityEntity>()
        );

        return new PageUtils(page);
    }

}
