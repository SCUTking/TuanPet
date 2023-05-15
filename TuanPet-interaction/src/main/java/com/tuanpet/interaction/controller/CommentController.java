package com.tuanpet.interaction.controller;

import java.util.*;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tuanpet.interaction.feign.UserInfoFeign;
import com.tuanpet.interaction.vo.ReqComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuanpet.interaction.entity.CommentEntity;
import com.tuanpet.interaction.service.CommentService;
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
@RequestMapping("interaction/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserInfoFeign userInfoFeign;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("interaction:comment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/listById")
    //@RequiresPermissions("interaction:comment:list")
    public R listById(@RequestParam Integer activityThoughtId){

        LambdaQueryWrapper<CommentEntity> QueryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper.eq(CommentEntity::getActivityThoughtId,activityThoughtId);
        List<CommentEntity> list = commentService.list(QueryWrapper);

        ArrayList<ReqComment> reqComments = new ArrayList<>();
        for (CommentEntity each:
             list) {
            ReqComment reqComment = new ReqComment();
            BeanUtils.copyProperties(each,reqComment);
            String userInfo = userInfoFeign.getUserInfo(each.getUserId());
            JSONObject jsonObject = JSON.parseObject(userInfo);
            JSONObject user = jsonObject.getJSONObject("user");
            reqComment.setUsername((String)user.get("nickname"));
            reqComment.setAvatarUrl((String)user.get("backgroundImage"));
            reqComments.add(reqComment);
        }




        return R.ok().put("commentList", reqComments);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{commentid}")
    //@RequiresPermissions("interaction:comment:info")
    public R info(@PathVariable("commentid") Integer commentid){
		CommentEntity comment = commentService.getById(commentid);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("interaction:comment:save")
    public R save(@RequestBody CommentEntity comment){
        comment.setUpdatedAt(new Date());
        comment.setCreatedAt(new Date());
		commentService.save(comment);

        return R.ok();
    }

    @RequestMapping("/action")
    //@RequiresPermissions("interaction:favoritecomment:save")
    public R action(@RequestParam Map<String, Object> params){

        int activityThoughtId=Integer.parseInt((String)params.get("activityThoughtId"));
        String actionType=(String) params.get("actionType");
        String commentText=(String) params.get("commentText");


        if(actionType.equals("0")){
            //add
            int userId=Integer.parseInt((String) params.get("userId"));

            //封装CommentEntity
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setComment(commentText);
            commentEntity.setActivityThoughtId(activityThoughtId);
            commentEntity.setFavoriteCount(0);
            commentEntity.setType("0");//0为text类型
            commentEntity.setUserId(userId);
            commentEntity.setUpdatedAt(new Date());
            commentEntity.setCreatedAt(new Date());
            commentService.save(commentEntity);
        }
        else{
            //actionType为“1”  删除评论
            Integer commentId=(Integer) params.get("commentId");
            commentService.removeById(commentId);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("interaction:comment:update")
    public R update(@RequestBody CommentEntity comment){
        comment.setUpdatedAt(new Date());
		commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("interaction:comment:delete")
    public R delete(@RequestBody Integer[] commentids){
		commentService.removeByIds(Arrays.asList(commentids));

        return R.ok();
    }

}
