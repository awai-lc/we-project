package com.hbzf.draw.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChoseMajorDto {

    /**
     * 专业编码
     */
    @NotNull
    private String majorCode;

    /**
     * 专业id
     */
    @NotNull
    private Long majorId;
    /**
     * 专业名称
     */
    @NotBlank
    private String majorName;

    /**
     * 已抽取专家数
     */
    @NotNull
    private Integer chosedCount;

    /**
     * 需抽取专家数
     */
    @NotNull
    private Integer needCount;

    /**
     * 库存专家数
     */
    @NotNull
    private Integer expertCount;

    /**
     * 所属区域名称
     */
    @NotBlank
    private String placeName;

}
