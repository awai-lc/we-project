package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.MajorEntity;

import java.util.List;
import java.util.Map;

/**
 * 专业
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
public interface MajorService extends IService<MajorEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPage(Map<String, Object> params,long parentId);
    List<MajorEntity> listWithTree();
}

