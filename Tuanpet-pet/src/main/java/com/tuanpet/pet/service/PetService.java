package com.tuanpet.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.pet.entity.PetEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-20 18:01:16
 */
public interface PetService extends IService<PetEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PetEntity> listByUserId(Integer userId);
}

