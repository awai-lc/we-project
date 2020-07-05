package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录用户表
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 名字
	 */
	private String nickname;
	/**
	 * 密码hash
	 */
	private String passwordHash;
	/**
	 * 活跃sessionId
	 */
	private String activeSessionId;
	/**
	 * 最近登录时间
	 */
	private Date lastLoginDt;
	/**
	 * 最近活跃时间
	 */
	private Date lastActiveDt;
	/**
	 * 
	 */
	private Integer isOnline;
	/**
	 * 新增人ID
	 */
	private Long addby;
	/**
	 * 新增时间
	 */
	private Date addbytime;
	/**
	 *  最近修改人ID
	 */
	private Long lastmodifiedby;
	/**
	 * 最近修改时间
	 */
	private Date lastmodifiedbytime;

}
