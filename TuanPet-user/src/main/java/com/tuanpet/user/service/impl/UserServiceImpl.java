package com.tuanpet.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tuanpet.common.commonVo.SocialUser;
import com.tuanpet.common.utils.*;
import com.tuanpet.user.vo.ReqUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.tuanpet.user.dao.UserDao;
import com.tuanpet.user.entity.UserEntity;
import com.tuanpet.user.service.UserService;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    public StringRedisTemplate redisTemplate;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ReqUser loginToGetToken(String code) throws Exception {

        SocialUser socialUser = JwtUtil.getSocialUser(code);
        String openid = socialUser.getOpenid();
        String session_key=socialUser.getSession_key();

        //下面有三处地方使用到
        LambdaQueryWrapper<UserEntity> userEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 查询redis是否有token

        String key = RedisConstants.LOGIN_USER_KEY+openid;
        String oldToken = redisTemplate.opsForValue().get(key);
        if(oldToken!=null){
            //如果有直接返回token，并延迟token时间
            //RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES   为了测试方便不设置过期时间
            redisTemplate.opsForValue().set(key,oldToken);

            userEntityLambdaQueryWrapper.eq(UserEntity::getOpenid,openid);
            UserEntity one = getOne(userEntityLambdaQueryWrapper);

            return new ReqUser(one.getUserId(),oldToken);
        }
        //如果没有,说明token过期，或者没有注册过,重新生成token


        String token = JwtUtil.getToken(socialUser.getOpenid(), socialUser.getSession_key());
        int userId;


        //查询数据库
        UserEntity userEntity =null;

        userEntityLambdaQueryWrapper.eq(UserEntity::getOpenid,openid);
        UserEntity one = getOne(userEntityLambdaQueryWrapper);
        if(one!=null){
            //存在
            userId= one.getUserId();
        }
        else{
            //不存在
            //用户注册
            SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
            userEntity=new UserEntity();
            userEntity.setOpenid(socialUser.getOpenid());
            userEntity.setCreatedAt(new Date());
            userEntity.setUpdatedAt(new Date());
            log.info(userEntity.toString());
            save(userEntity);

            //获取用户Id
            userEntityLambdaQueryWrapper.eq(UserEntity::getOpenid,socialUser.getOpenid());
            UserEntity user = getOne(userEntityLambdaQueryWrapper);
            userId=user.getUserId();
        }


        // 写入redis缓存
//        redisTemplate.opsForValue().set(key,token,RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(key,token);


        return new ReqUser(userId,token);
    }



}
