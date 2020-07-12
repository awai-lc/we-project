package com.hbzf.draw.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChoseMajorDto {

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
     * 需抽取专家数
     */
    @NotNull
    private Integer needCount;


    /**
     * 是否删除
     **/
    private Integer isDelete;


}
