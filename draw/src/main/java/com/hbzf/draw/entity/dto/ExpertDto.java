package com.hbzf.draw.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author harryper
 * @description 专家dto
 * @date 2020/7/12
 */
@Data
public class ExpertDto {

    private Long expertId;
    /**
     * 专家名称
     */
    private String expertName;
    /**
     * 专业id
     */
    private Long majorId;
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
