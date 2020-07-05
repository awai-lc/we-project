package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 专业
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
@Data
@TableName("major")
public class MajorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 专业编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String majorName;
	/**
	 * 父ID
	 */
	private Long parentId;

}