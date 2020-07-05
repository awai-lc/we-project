package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 选中专业
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:00
 */
@Data
@TableName("chose_major")
public class ChoseMajorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 专业id
	 */
	private Long majorId;
	/**
	 * 专业编码
	 */
	private String majorCode;
	/**
	 * 项目id
	 */
	private Long proId;
	/**
	 * 专业名称
	 */
	private String majorName;
	/**
	 * 已抽取专家数
	 */
	private Integer chosedCount;
	/**
	 * 需抽取专家数
	 */
	private Integer needCount;
	/**
	 * 库存专家数
	 */
	private Integer expertCount;
	/**
	 * 所属区域名称
	 */
	private String placeName;

}
