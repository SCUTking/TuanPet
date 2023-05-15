package com.tuanpet.activity.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.tuanpet.activity.service.ActivityService;
import com.tuanpet.activity.entity.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2023-05-15 10:23:05
 */
@RestController
@RequestMapping("activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("activity:activity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = activityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{activityid}")
    //@RequiresPermissions("activity:activity:info")
    public R info(@PathVariable("activityid") Integer activityid){
		ActivityEntity activity = activityService.getById(activityid);

        return R.ok().put("activity", activity);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    //@RequiresPermissions("activity:activity:info")
    public R infoById(@RequestParam Integer activityId){
        ActivityEntity activity = activityService.getById(activityId);

        return R.ok().put("activity", activity);
    }


    /**
     * 获取全部
     *
     * @return
     */
    @RequestMapping("/getAll")
    //@RequiresPermissions("activity:activity:info")
    public R infoGetAll(){
        List<ActivityEntity> activityEntityList = activityService.list();


        return R.ok().put("activityList", activityEntityList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("activity:activity:save")
    public R save(@RequestBody ActivityEntity activity){
		activity.setUpdatedAt(new Date());
        activity.setCreatedAt(new Date());
        activityService.save(activity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("activity:activity:update")
    public R update(@RequestBody ActivityEntity activity){
        activity.setUpdatedAt(new Date());

		activityService.updateById(activity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("activity:activity:delete")
    public R delete(@RequestBody Integer[] activityids){
		activityService.removeByIds(Arrays.asList(activityids));

        return R.ok();
    }

    @RequestMapping("/deleteById")
    //@RequiresPermissions("activity:activity:delete")
    public R deleteById(@RequestParam Integer activityId){
        activityService.removeById(activityId);

        return R.ok();
    }

}
