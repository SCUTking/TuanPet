package com.tuanpet.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author lqh
 * @email 3220497145@qq.com
 * @date 2023-03-19 12:36:54
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 *Id
	 */
	@TableId
	private Integer userId;
	/**
	 *昵称
	 */
	private String nickname;
	/**
	 *密码
	 */
	private String password;
	/**
	 *微信
	 */
	private String wechat;
	/**
	 *手机号
	 */
	private String phone;
	/**
	 *生日
	 */
	private String birthday;
	/**
	 * 会员等级
	 */
	private String level;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 头像
	 */
	private String backgroundImage;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String address;
	/**
	 * 积分
	 */
	private String integration;
	/**
	 * 微信平台用户的唯一id
	 */
	private String openid;
	/**
	 *创建时间
	 */
	private Date createdat;
	/**
	 *修改时间
	 */
	private Date updatedat;

}
