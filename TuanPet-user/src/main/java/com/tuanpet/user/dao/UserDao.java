package com.tuanpet.user.dao;

import com.tuanpet.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-19 12:36:54
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
