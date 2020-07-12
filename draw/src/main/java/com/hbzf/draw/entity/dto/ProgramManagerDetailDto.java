package com.hbzf.draw.entity.dto;

import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.entity.ProgramManagerEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProgramManagerDetailDto {
    private ProgramManagerEntity programManager;

    private List<ChoseMajorEntity> choseMajorEntities;
}
