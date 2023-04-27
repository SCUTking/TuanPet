package com.tuanpet.user.controller;

import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;
import com.tuanpet.common.utils.RedisConstants;
import com.tuanpet.common.utils.SnowflakeIdWorker;
import com.tuanpet.user.entity.UserEntity;
import com.tuanpet.user.service.UserService;


import com.tuanpet.user.vo.ReqUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:user:info")
    public R info(@PathVariable("id") Integer id){
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }


    /**
     * B端获取用户信息
     */
    @RequestMapping("/info")
    //@RequiresPermissions("user:user:info")
    public R getinfo(@RequestParam Integer userId){
        UserEntity user = userService.getById(userId);
        //处理返回的user
        user.setPassword("");

        return R.ok().put("user", user);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:user:save")
    public R save(@RequestBody UserEntity user){
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        log.info(user.toString());
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:user:update")
    public R update(@RequestBody UserEntity user){
        user.setUpdatedAt(new Date());
        log.info(user.toString());
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:user:delete")
    public R delete(@RequestBody Integer[] ids){
        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



    /**
     *
     * @param code 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     * @return
     */
    @GetMapping("/login")
    public R login(@RequestParam String code) throws Exception {

        ReqUser reqUser = userService.loginToGetToken(code);

        return  R.ok().put("token",reqUser.getToken()).put("userId",reqUser.getUserId());

    }

}
