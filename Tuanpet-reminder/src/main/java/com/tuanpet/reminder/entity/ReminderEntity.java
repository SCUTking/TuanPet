package com.tuanpet.reminder.entity;

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
 * @date 2023-03-20 23:40:23
 */
@Data
@TableName("reminder")
public class ReminderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId
	private Integer reminderId;//与数据库中的字段保持一模一样
	/**
	 * 宠物Id
	 */
	private Integer petId;//与数据库中的字段保持一模一样
	/**
	 * 用户Id
	 */
	private Integer userId;//与数据库中的字段保持一模一样
	/**
	 * 提醒的名字
	 */
	private String reminderName;//与数据库中的字段保持一模一样
	/**
	 * 提醒的类型
	 */
	private String type;//与数据库中的字段保持一模一样
	/**
	 * 提醒的内容
	 */
	private String content;//与数据库中的字段保持一模一样
	/**
	 * 提醒的计划日期
	 */
	private String plannedDate;//与数据库中的字段保持一模一样
	/**
	 * 周期
	 */
	private String period;//与数据库中的字段保持一模一样
	/**
	 * 是否已经完成
	 */
	private String isDone;//与数据库中的字段保持一模一样
	/**
	 * 提前几天提醒
	 */
	private String advanceDay;//与数据库中的字段保持一模一样
	/**
	 * 在计划日期的哪个时间提醒
	 */
	private String reminderTimeMoment;//与数据库中的字段保持一模一样
	/**
	 * 创建时间
	 */
	private Date createdAt;//与数据库中的字段保持一模一样
	/**
	 * 修改时间
	 */
	private Date updatedAt;//与数据库中的字段保持一模一样

}
