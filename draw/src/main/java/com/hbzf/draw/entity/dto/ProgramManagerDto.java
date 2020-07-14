package com.hbzf.draw.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProgramManagerDto {

    /**
     * 主键
     */
    private Long id;

    /**
     * 项目编码
     */
    private String code;

    /**
     * 项目名称
     */
    @NotBlank
    private String name;

    /**
     * 项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）
     */
    @NotNull
    private Integer proStatus;

    /**
     * 采购单位ID
     */
    @NotBlank
    private String purchasingId;

    /**
     * 抽取单位ID
     */
    @NotBlank
    private String extractionUnit;

    /**
     * 采购方式ID
     */
    @NotNull
    private Long purWay;

    /**
     * 评审开始时间
     */
    @NotNull
    private Date startReview;

    /**
     * 评审结束时间
     */
    @NotNull
    private Date endReview;

    /**
     * 采购备案号
     */
    @NotBlank
    private String govProRecordNumber;

    /**
     * 参与采购评审人代表
     */
    @NotBlank
    private String govProPerson;

    /**
     * 评审内容
     */
    @NotBlank
    private String reviewContent;

    /**
     * 预算金额
     */
    @NotNull
    private BigDecimal budgetAmount;

    /**
     * 抽取单位联系人
     */
    @NotBlank
    private String extractionUnitContact;

    /**
     * 抽取单位电话
     */
    @NotBlank
    private String extractionUnitPhone;

    /**
     * 评审地址
     */
    @NotBlank
    private String address;

    /**
     * 项目所属监管地ID
     */
    @NotNull
    private Long supervisoryPlaceId;

    /**
     * 回避专家单位
     */
    private List<String> avoidUnit;

    /**
     * 备注
     */
    private String remark;
}
