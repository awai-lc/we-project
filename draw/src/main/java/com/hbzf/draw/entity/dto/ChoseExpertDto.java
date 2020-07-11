package com.hbzf.draw.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专家
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:59
 */
@Data
public class ChoseExpertDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 专家名称
	 */
	private String expertName;
	/**
	 * 专业id
	 */
	private Long majorId;

	/**
	 * 专业名称
	 */
	private String majorName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 工作单位
	 */
	private String unit;
	/**
	 * 在职状态（1退休，2在职）
	 */
	private Integer jobStatus;
	/**
	 * 出生年月
	 */
	private String birth;
	/**
	 * 职称
	 */
	private String title;

	/**
	 * 抽取时间
	 */
	private String addbytime;

}
