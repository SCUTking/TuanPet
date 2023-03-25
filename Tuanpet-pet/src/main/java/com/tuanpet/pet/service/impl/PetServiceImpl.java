package com.tuanpet.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;

import com.tuanpet.pet.dao.PetDao;
import com.tuanpet.pet.entity.PetEntity;
import com.tuanpet.pet.service.PetService;


@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetDao, PetEntity> implements PetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PetEntity> page = this.page(
                new Query<PetEntity>().getPage(params),
                new QueryWrapper<PetEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PetEntity> listByUserId(Integer userId) {
        LambdaQueryWrapper<PetEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PetEntity::getUserId,userId);
        List<PetEntity> list = list(queryWrapper);
        return list;
    }
}
