package com.tuanpet.activitythought.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.tuanpet.activitythought.JsonToStringArray.JsonArrayDeserializer;
import com.tuanpet.activitythought.JsonToStringArray.JsonStringArrayTypeHandler;
import lombok.Data;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-04-28 20:24:08
 */
@Data
@TableName("activityThought")
public class ActivitythoughtEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer activityThoughtId;//与数据库中的字段保持一模一样
	/**
	 * 用户Id
	 */
	private Integer userId;//与数据库中的字段保持一模一样
	/**
	 * 活动Id
	 */
	private String activityId;//与数据库中的字段保持一模一样
	/**
	 * 活动的名称
	 */
	private String activityName;//与数据库中的字段保持一模一样
	/**
	 * 内容
	 */
	private String content;//与数据库中的字段保持一模一样
	/**
	 * 星球类别，星球社区时用到
	 */
	private String classify;//与数据库中的字段保持一模一样
	/**
	 * 记录点赞数
	 */
	private String favoriteCount;//与数据库中的字段保持一模一样
	/**
	 * 记录评论数
	 */
	private String commentCount;//与数据库中的字段保持一模一样
	/**
	 * 图片集
	 */
//	@JSONField(deserializeUsing = JsonArrayDeserializer.class)
	@TableField(value = "photos",typeHandler = JsonStringArrayTypeHandler.class)
	private String[] photos;//与数据库中的字段保持一模一样
	/**
	 * 视频集
	 */
//	@JSONField(deserializeUsing = JsonArrayDeserializer.class)
	@TableField(value = "videos",typeHandler = JsonStringArrayTypeHandler.class)
	private String[] videos;//与数据库中的字段保持一模一样
	/**
	 * 发布地址
	 */
	private String location;//与数据库中的字段保持一模一样
	/**
	 *
	 */
	private Date createdAt;//与数据库中的字段保持一模一样
	/**
	 *
	 */
	private Date updatedAt;//与数据库中的字段保持一模一样

}
