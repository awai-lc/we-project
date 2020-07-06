package com.hbzf.draw.entity.request;


import com.hbzf.draw.entity.dto.ChoseMajorDto;
import com.hbzf.draw.entity.dto.ProgramManagerDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProgramManagerRequest{

    @NotNull(message = "参数不能为空")
    private ProgramManagerDto programManager;

    @NotEmpty(message = "majorList 不能为空")
    private List<ChoseMajorDto> majorList;
}
