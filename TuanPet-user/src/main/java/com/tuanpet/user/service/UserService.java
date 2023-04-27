package com.tuanpet.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;
import com.tuanpet.user.entity.UserEntity;
import com.tuanpet.user.vo.ReqUser;

import java.util.Map;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-19 12:36:54
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ReqUser loginToGetToken(String code) throws Exception;
}

