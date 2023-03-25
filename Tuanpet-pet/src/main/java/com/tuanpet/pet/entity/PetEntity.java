package com.tuanpet.pet.entity;

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
 * @date 2023-03-20 18:01:16
 */
@Data
@TableName("pet")
public class PetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Id
	 */
	@TableId
	private long petId;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 接回日期
	 */
	private String dateOfArrival;
	/**
	 * 是否绝育
	 */
	private String isSterilization;

	/**
	 * 名字
	 */
	private String petName;
	/**
	 * 头像
	 */
	private String petPhoto;
	/**
	 * 品种
	 */
	private String petType;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 体重
	 */
	private String weight;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 修改时间
	 */
	private Date updatedAt;

}
