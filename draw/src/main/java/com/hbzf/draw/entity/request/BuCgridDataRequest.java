package com.hbzf.draw.entity.request;

import com.hbzf.draw.entity.dto.BuCgridDataDto;
import lombok.Data;

import java.util.List;

@Data
public class BuCgridDataRequest {
    private Long proId;

    private List<BuCgridDataDto> buCgridData;
}
