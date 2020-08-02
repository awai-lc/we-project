package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.ExpertEntity;

import java.util.List;
import java.util.Map;

/**
 * 专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:59
 */
public interface ExpertService extends IService<ExpertEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<List<String>> batchInsertByLists(List<List<String>> values);
}

