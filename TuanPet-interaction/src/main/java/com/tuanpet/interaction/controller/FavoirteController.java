package com.tuanpet.interaction.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.interaction.entity.FavoirteEntity;
import com.tuanpet.interaction.service.FavoirteService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;



/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-05-15 12:57:58
 */
@RestController
@RequestMapping("interaction/favoirte")
public class FavoirteController {
    @Autowired
    private FavoirteService favoirteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("interaction:favoirte:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = favoirteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{favoriteid}")
    //@RequiresPermissions("interaction:favoirte:info")
    public R info(@PathVariable("favoriteid") Integer favoriteid){
		FavoirteEntity favoirte = favoirteService.getById(favoriteid);

        return R.ok().put("favoirte", favoirte);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("interaction:favoirte:save")
    public R save(@RequestBody FavoirteEntity favoirte){
        favoirte.setUpdatedAt(new Date());
        favoirte.setCreatedAt(new Date());
		favoirteService.save(favoirte);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("interaction:favoirte:update")
    public R update(@RequestBody FavoirteEntity favoirte){
        favoirte.setUpdatedAt(new Date());
		favoirteService.updateById(favoirte);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("interaction:favoirte:delete")
    public R delete(@RequestBody Integer[] favoriteids){
		favoirteService.removeByIds(Arrays.asList(favoriteids));

        return R.ok();
    }

}
