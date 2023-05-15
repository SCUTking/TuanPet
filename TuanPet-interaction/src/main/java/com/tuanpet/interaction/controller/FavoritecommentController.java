package com.tuanpet.interaction.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.interaction.entity.FavoritecommentEntity;
import com.tuanpet.interaction.service.FavoritecommentService;
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
@RequestMapping("interaction/favoriteComment")
public class FavoritecommentController {
    @Autowired
    private FavoritecommentService favoritecommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("interaction:favoritecomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = favoritecommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{favoriteid}")
    //@RequiresPermissions("interaction:favoritecomment:info")
    public R info(@PathVariable("favoriteid") Integer favoriteid){
		FavoritecommentEntity favoritecomment = favoritecommentService.getById(favoriteid);

        return R.ok().put("favoritecomment", favoritecomment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("interaction:favoritecomment:save")
    public R save(@RequestBody FavoritecommentEntity favoritecomment){
		favoritecommentService.save(favoritecomment);

        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/action")
    //@RequiresPermissions("interaction:favoritecomment:save")
    public R action(@RequestParam Map<String, Object> params){
        Integer commentId=(Integer) params.get("commentId");
        Integer userId=(Integer) params.get("userId");
        String actionType = (String) params.get("actionType");

        //点赞
        LambdaQueryWrapper<FavoritecommentEntity> eq = new LambdaQueryWrapper<FavoritecommentEntity>().eq(FavoritecommentEntity::getUserId, userId);
        FavoritecommentEntity byId = favoritecommentService.getById(eq);

        if(actionType.equals("1")){




            //如果数据库中没有数据
            if(byId==null){
                FavoritecommentEntity favoritecommentEntity = new FavoritecommentEntity();
                favoritecommentEntity.setIsFavorite("1");
                favoritecommentEntity.setCommentId(commentId);
                favoritecommentEntity.setUserId(userId);
                favoritecommentEntity.setCreatedAt(new Date());
                favoritecommentEntity.setUpdatedAt(new Date());
                favoritecommentService.save(favoritecommentEntity);
            }
            else {
                //已经有了就是更新
                byId.setIsFavorite("1");
                favoritecommentService.updateById(byId);
            }



        }
        else{
            //点赞取消
            //已经有了就是更新
            byId.setIsFavorite("0");
            favoritecommentService.updateById(byId);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("interaction:favoritecomment:update")
    public R update(@RequestBody FavoritecommentEntity favoritecomment){
        favoritecomment.setUpdatedAt(new Date());
		favoritecommentService.updateById(favoritecomment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("interaction:favoritecomment:delete")
    public R delete(@RequestBody Integer[] favoriteids){
		favoritecommentService.removeByIds(Arrays.asList(favoriteids));

        return R.ok();
    }

}
