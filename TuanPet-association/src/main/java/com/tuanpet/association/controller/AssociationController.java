package com.tuanpet.association.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.association.entity.AssociationEntity;
import com.tuanpet.association.service.AssociationService;
import com.tuanpet.common.utils.PageUtils;
import com.tuanpet.common.utils.R;



/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-22 15:22:48
 */
@RestController
@RequestMapping("association")
public class AssociationController {
    @Autowired
    private AssociationService associationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("association:association:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = associationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{associationid}")
    //@RequiresPermissions("association:association:info")
    public R info(@PathVariable("associationid") Integer associationid){
		AssociationEntity association = associationService.getById(associationid);

        return R.ok().put("association", association);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    //@RequiresPermissions("association:association:info")
    public R infoById(@RequestParam String associationId){
        AssociationEntity association = associationService.getById(associationId);

        return R.ok().put("association", association);
    }


    /**
     * 获取全部信息
     */
    @RequestMapping("/getAll")
    //@RequiresPermissions("association:association:info")
    public R getAll(){
        List<AssociationEntity> associationEntities= associationService.getAll();

        return R.ok().put("associationList", associationEntities);
    }


    /**
     * 通过地方获取社群
     */
    @RequestMapping("/infoByProvince")
    //@RequiresPermissions("association:association:info")
    public R getAll(@RequestParam String province){
        LambdaQueryWrapper<AssociationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssociationEntity::getArea,province);
        AssociationEntity one = associationService.getOne(queryWrapper);
        return R.ok().put("association", one);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("association:association:save")
    public R save(@RequestBody AssociationEntity association){
        association.setCreatedAt(new Date());
        association.setUpdatedAt(new Date());
		associationService.save(association);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("association:association:update")
    public R update(@RequestBody AssociationEntity association){
        association.setUpdatedAt(new Date());
		associationService.updateById(association);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("association:association:delete")
    public R delete(@RequestBody Integer[] associationids){
		associationService.removeByIds(Arrays.asList(associationids));

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/deleteById")
    //@RequiresPermissions("association:association:delete")
    public R delete(@RequestParam String associationId){
        int id = Integer.parseInt(associationId);
        associationService.removeById(id);

        return R.ok();
    }

}
