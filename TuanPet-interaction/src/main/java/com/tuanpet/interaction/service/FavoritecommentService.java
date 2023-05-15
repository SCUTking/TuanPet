package com.tuanpet.interaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.interaction.entity.FavoritecommentEntity;

import java.util.Map;

/**
 * 
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-05-15 12:57:58
 */
public interface FavoritecommentService extends IService<FavoritecommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

