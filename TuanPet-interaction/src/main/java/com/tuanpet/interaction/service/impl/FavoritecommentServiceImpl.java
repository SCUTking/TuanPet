package com.tuanpet.interaction.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;

import com.tuanpet.interaction.dao.FavoritecommentDao;
import com.tuanpet.interaction.entity.FavoritecommentEntity;
import com.tuanpet.interaction.service.FavoritecommentService;


@Service("favoritecommentService")
public class FavoritecommentServiceImpl extends ServiceImpl<FavoritecommentDao, FavoritecommentEntity> implements FavoritecommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoritecommentEntity> page = this.page(
                new Query<FavoritecommentEntity>().getPage(params),
                new QueryWrapper<FavoritecommentEntity>()
        );

        return new PageUtils(page);
    }

}