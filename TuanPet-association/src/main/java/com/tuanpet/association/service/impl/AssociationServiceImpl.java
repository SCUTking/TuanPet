package com.tuanpet.association.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.Query;

import com.tuanpet.association.dao.AssociationDao;
import com.tuanpet.association.entity.AssociationEntity;
import com.tuanpet.association.service.AssociationService;


@Service("associationService")
public class AssociationServiceImpl extends ServiceImpl<AssociationDao, AssociationEntity> implements AssociationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AssociationEntity> page = this.page(
                new Query<AssociationEntity>().getPage(params),
                new QueryWrapper<AssociationEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<AssociationEntity> getAll() {
        LambdaQueryWrapper<AssociationEntity> queryWrapper = new LambdaQueryWrapper<>();
        List<AssociationEntity> list = list(queryWrapper);
        return list;
    }
}
