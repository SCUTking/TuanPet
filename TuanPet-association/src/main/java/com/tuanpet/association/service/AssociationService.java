package com.tuanpet.association.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.association.entity.AssociationEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-22 15:22:48
 */
public interface AssociationService extends IService<AssociationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AssociationEntity> getAll();
}

