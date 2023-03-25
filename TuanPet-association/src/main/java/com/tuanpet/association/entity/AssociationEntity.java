package com.tuanpet.association.entity;

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
 * @date 2023-03-22 15:22:48
 */
@Data
@TableName("association")
public class AssociationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 社群id号
	 */
	@TableId
	private Integer associationId;//与数据库中的字段保持一模一样
	/**
	 * 星球类别
	 */
	private String classify;//与数据库中的字段保持一模一样
	/**
	 * 地区
	 */
	private String area;//与数据库中的字段保持一模一样
	/**
	 * 二维码url
	 */
	private String img;//与数据库中的字段保持一模一样
	/**
	 * 创建时间
	 */
	private Date createdAt;//与数据库中的字段保持一模一样
	/**
	 * 修改时间
	 */
	private Date updatedAt;//与数据库中的字段保持一模一样

}
