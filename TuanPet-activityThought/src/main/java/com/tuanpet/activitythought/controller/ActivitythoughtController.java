package com.tuanpet.activitythought.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.tuanpet.activitythought.entity.ActivitythoughtEntity;
import com.tuanpet.activitythought.service.ActivitythoughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;



/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-04-28 20:24:08
 */
@RestController
@RequestMapping("activityThought")
public class ActivitythoughtController {
    @Autowired
    private ActivitythoughtService activitythoughtService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("activityThought:activitythought:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = activitythoughtService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{activitythoughtid}")
    //@RequiresPermissions("activityThought:activitythought:info")
    public R info(@PathVariable("activitythoughtid") Integer activitythoughtid){
		ActivitythoughtEntity activitythought = activitythoughtService.getById(activitythoughtid);

        return R.ok().put("activityThought", activitythought);
    }

    @RequestMapping("/info")
    //@RequiresPermissions("activityThought:activitythought:info")
    public R getInfoById(@RequestParam String activityThoughtId){

        //TODO 前端字符串数组可以转换为JSON了，但是后端无法反序列化为STring[],即photos等为空。
        //用指定的JSON反序列化
        ActivitythoughtEntity activityThoughtById = activitythoughtService.getActivityThoughtById(Long.parseLong(activityThoughtId));

        return R.ok().put("activityThought", activityThoughtById);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("activityThought:activitythought:save")
    public R save(@RequestBody Map<String, Object> requestMap){

        String activityName = (String)requestMap.get("activityName");
        String location = (String)requestMap.get("location");
        Integer userId = (Integer)requestMap.get("userId");
        String content = (String)requestMap.get("content");

        ActivitythoughtEntity activitythought = new ActivitythoughtEntity();
        activitythought.setActivityName(activityName);
        activitythought.setContent(content);
        activitythought.setUserId(userId);
        activitythought.setLocation(location);



        String data = JSON.toJSONString(requestMap.get("data"));
        String substring = data.substring(1, data.length() - 1);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String sub = split[i].substring(1, split[i].length() - 1);
            split[i]=sub;
        }
        activitythought.setPhotos(split);


        activitythought.setCreatedAt(new Date());
        activitythought.setUpdatedAt(new Date());
        //将获取到的图片和视频的地址进行处理
        //上传OSS，并把地址保存到数据库中  方便返回的地址在前端可用

		activitythoughtService.save(activitythought);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("activityThought:activitythought:update")
    public R update(@RequestBody ActivitythoughtEntity activitythought){
        activitythought.setCreatedAt(new Date());
        activitythought.setUpdatedAt(new Date());
		activitythoughtService.updateById(activitythought);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("activityThought:activitythought:delete")
    public R delete(@RequestBody Integer[] activitythoughtids){
		activitythoughtService.removeByIds(Arrays.asList(activitythoughtids));

        return R.ok();
    }

}
