package com.tuanpet.interaction.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;

import com.tuanpet.interaction.dao.FavoirteDao;
import com.tuanpet.interaction.entity.FavoirteEntity;
import com.tuanpet.interaction.service.FavoirteService;


@Service("favoirteService")
public class FavoirteServiceImpl extends ServiceImpl<FavoirteDao, FavoirteEntity> implements FavoirteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoirteEntity> page = this.page(
                new Query<FavoirteEntity>().getPage(params),
                new QueryWrapper<FavoirteEntity>()
        );

        return new PageUtils(page);
    }

}