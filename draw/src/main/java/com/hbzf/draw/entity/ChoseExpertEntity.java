package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 选中专家
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
@Data
@TableName("chose_expert")
public class ChoseExpertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;

	/**
	 * 专家id
	 */
	private Long expertId;
	/**
	 * 专业id
	 */
	private Long majorId;
	/**
	 * 项目id
	 */
	private Long proId;
	/**
	 * 是否删除（0否， 1是）
	 */
	private Integer isDelete;
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
