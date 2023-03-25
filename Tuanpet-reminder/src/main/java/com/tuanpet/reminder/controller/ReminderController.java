package com.tuanpet.reminder.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.reminder.entity.ReminderEntity;
import com.tuanpet.reminder.service.ReminderService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;



/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-20 22:09:26
 */
@RestController
@RequestMapping("reminder")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("reminder:reminder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reminderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/listByUserId")
    //@RequiresPermissions("reminder:reminder:list")
    public R listByUserId(@RequestParam String userId){
        int id = Integer.parseInt(userId);
        List<ReminderEntity> reminderEntities = reminderService.listByUserId(id);

        return R.ok().put("reminderList", reminderEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reminderid}")
    //@RequiresPermissions("reminder:reminder:info")
    public R info(@PathVariable("reminderid") Integer reminderid){
		ReminderEntity reminder = reminderService.getById(reminderid);

        return R.ok().put("reminder", reminder);
    }


    @RequestMapping("/info")
    //@RequiresPermissions("reminder:reminder:info")
    public R infoById( @RequestParam String reminderId){
        int id = Integer.parseInt(reminderId);
        ReminderEntity reminder = reminderService.getById(id);

        return R.ok().put("reminder", reminder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("reminder:reminder:save")
    public R save(@RequestBody ReminderEntity reminder){
        reminder.setCreatedAt(new Date());
        reminder.setUpdatedAt(new Date());
		reminderService.save(reminder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("reminder:reminder:update")
    public R update(@RequestBody ReminderEntity reminder){
        reminder.setUpdatedAt(new Date());
		reminderService.updateById(reminder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("reminder:reminder:delete")
    public R delete(@RequestBody Integer[] reminderids){
		reminderService.removeByIds(Arrays.asList(reminderids));

        return R.ok();
    }


    @RequestMapping("/deleteById")
    //@RequiresPermissions("reminder:reminder:delete")
    public R delete(@RequestParam String reminderId){
        int id = Integer.parseInt(reminderId);

        reminderService.removeById(id);

        return R.ok();
    }

}
