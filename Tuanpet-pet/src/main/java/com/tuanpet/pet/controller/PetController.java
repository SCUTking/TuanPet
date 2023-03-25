package com.tuanpet.pet.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.pet.entity.PetEntity;
import com.tuanpet.pet.service.PetService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;



/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-20 18:01:16
 */
@RestController
@RequestMapping("pet")
public class PetController {
    @Autowired
    private PetService petService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("pet:pet:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = petService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据用户的Id获得宠物列表
     */
    @RequestMapping("/listByUserId")
    public R listByUserId(@RequestParam String userId){
        int id = Integer.parseInt(userId);
        List<PetEntity> petEntities = petService.listByUserId(id);


        return R.ok().put("petList",petEntities);
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{petid}")
    //@RequiresPermissions("pet:pet:info")
    public R info(@PathVariable("petid") String petid){
		PetEntity pet = petService.getById(petid);

        return R.ok().put("pet", pet);
    }

    @RequestMapping("/info")
    //@RequiresPermissions("pet:pet:info")
    public R getInfoByQueryId(@RequestParam  String petId){
        PetEntity pet = petService.getById(petId);

        return R.ok().put("pet", pet);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("pet:pet:save")
    public R save(@RequestBody PetEntity pet){
        pet.setCreatedAt(new Date());
        pet.setUpdatedAt(new Date());
		petService.save(pet);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("pet:pet:update")
    public R update(@RequestBody PetEntity pet){
        pet.setUpdatedAt(new Date());
		petService.updateById(pet);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("pet:pet:delete")
    public R delete(@RequestBody Integer[] petids){
		petService.removeByIds(Arrays.asList(petids));

        return R.ok();
    }

    @RequestMapping("/deleteById")
    //@RequiresPermissions("pet:pet:delete")
    public R deleteByQueryId(@RequestParam Integer petId){
        petService.removeById(petId);

        return R.ok();
    }


}
