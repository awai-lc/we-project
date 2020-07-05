package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 单位
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
@Data
@TableName("unit")
public class UnitEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 单位名称
	 */
	private String unitName;
	/**
	 * 新增人ID
	 */
	private Long addby;
	/**
	 * 新增时间
	 */
	private Date addbytime;

}
