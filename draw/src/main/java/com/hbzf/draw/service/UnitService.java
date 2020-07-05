package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.UnitEntity;

import java.util.Map;

/**
 * 单位
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
public interface UnitService extends IService<UnitEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

