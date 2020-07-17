package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.entity.dto.ChoseMajorDto;

import java.util.List;
import java.util.Map;

/**
 * 选中专业
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:00
 */
public interface ChoseMajorService extends IService<ChoseMajorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ChoseMajorEntity> getByProId(Long id);

    List<ChoseMajorDto> listByProId(Long proID);
}

