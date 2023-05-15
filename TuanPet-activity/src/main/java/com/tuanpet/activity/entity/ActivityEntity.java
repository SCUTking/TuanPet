package com.tuanpet.activity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-05-15 10:23:05
 */
@Data
@TableName("activity")
public class ActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id

	 */
	@TableId
	private Integer activityId;//与数据库中的字段保持一模一样
	/**
	 * 活动海报
	 */
	private String poster;//与数据库中的字段保持一模一样
	/**
	 * 活动热度
	 */
	private Integer heat;//与数据库中的字段保持一模一样
	/**
	 * 活动名称
	 */
	private String activityName;//与数据库中的字段保持一模一样
	/**
	 * 活动的排名
	 */
	private Integer activitySort;//与数据库中的字段保持一模一样
	/**
	 *
	 */
	private Date createdAt;//与数据库中的字段保持一模一样
	/**
	 *
	 */
	private Date updatedAt;//与数据库中的字段保持一模一样

}
