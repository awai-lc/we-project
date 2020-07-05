package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 监管地
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
@Data
@TableName("supervisory_place")
public class SupervisoryPlaceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 监管地名称
	 */
	private String placeName;

}
