package com.hbzf.draw.entity.response;

import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:59
 */
@Data
public class ChoseExpertDtoResponse implements Serializable {
	private static final long serialVersionUID = 1L;

    private ProgramManagerEntity programManager;


    private List<ChoseExpertDto> choseExpertDtoList;

}
