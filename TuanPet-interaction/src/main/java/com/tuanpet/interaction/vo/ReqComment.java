package com.tuanpet.interaction.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReqComment {
    private Integer commentId;//与数据库中的字段保持一模一样
    /**
     * 活动笔记Id
     */
    private Integer activityThoughtId;//与数据库中的字段保持一模一样
    /**
     * 用户Id
     */
    private Integer userId;//与数据库中的字段保持一模一样
    /**
     * 评论
     */
    private String comment;//与数据库中的字段保持一模一样
    /**
     * 删除时间
     */
    private String deletedAt;//与数据库中的字段保持一模一样
    /**
     * 创建时间
     */
    private Date createdAt;//与数据库中的字段保持一模一样
    /**
     * 修改时间
     */
    private Date updatedAt;//与数据库中的字段保持一模一样
    /**
     * 评论类型
     */
    private String type;//与数据库中的字段保持一模一样
    /**
     * 点赞数量
     */
    private Integer favoriteCount;//与数据库中的字段保持一模一样

    private String username;

    private String avatarUrl;
}
