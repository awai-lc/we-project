package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.SupervisoryPlaceEntity;

import java.util.Map;

/**
 * 监管地
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
public interface SupervisoryPlaceService extends IService<SupervisoryPlaceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

