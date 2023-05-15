package com.tuanpet.interaction.entity;

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
 * @date 2023-05-15 12:57:58
 */
@Data
@TableName("favoirte")
public class FavoirteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer favoriteId;//与数据库中的字段保持一模一样
	/**
	 * 活动笔记Id
	 */
	private Integer activityThoughtId;//与数据库中的字段保持一模一样
	/**
	 * 用户Id
	 */
	private Integer userId;//与数据库中的字段保持一模一样
	/**
	 * 是否点赞
	 */
	private String isFavorite;//与数据库中的字段保持一模一样
	/**
	 * 创建时间
	 */
	private Date createdAt;//与数据库中的字段保持一模一样
	/**
	 * 修改时间
	 */
	private Date updatedAt;//与数据库中的字段保持一模一样

}
