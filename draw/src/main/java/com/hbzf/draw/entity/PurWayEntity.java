package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 采购方式
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:57
 */
@Data
@TableName("pur_way")
public class PurWayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 采购方式（公开招标，竞争性谈判，竞争性磋商，询价采购，其他）
	 */
	private String purWay;

}
