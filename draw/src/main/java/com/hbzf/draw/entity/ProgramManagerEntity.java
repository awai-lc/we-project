package com.hbzf.draw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 项目表
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
@Data
@TableName("program_manager")
public class ProgramManagerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 项目编码
	 */
	private String code;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）
	 */
	private Integer proStatus;
	/**
	 * 采购单位ID
	 */
	private Long purchasingId;
	/**
	 * 抽取单位ID
	 */
	private String extractionUnit;
	/**
	 * 采购方式ID
	 */
	private Long purWay;
	/**
	 * 评审开始时间
	 */
	private Date startReview;
	/**
	 * 评审结束时间
	 */
	private Date endReview;
	/**
	 * 采购备案号
	 */
	private String govProRecordNumber;
	/**
	 * 参与采购评审人代表
	 */
	private String govProPerson;
	/**
	 * 评审内容
	 */
	private String reviewContent;
	/**
	 * 预算金额
	 */
	private BigDecimal budgetAmount;
	/**
	 * 抽取单位联系人
	 */
	private String extractionUnitContact;
	/**
	 * 抽取单位电话
	 */
	private String extractionUnitPhone;
	/**
	 * 评审地址
	 */
	private String address;
	/**
	 * 项目所属监管地ID
	 */
	private Long supervisoryPlaceId;
	/**
	 * 回避专家单位
	 */
	private String avoidUnit;
	/**
	 * 备注
	 */
	private String remark;
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
