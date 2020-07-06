package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.entity.request.ProgramManagerRequest;

import java.util.Map;

/**
 * 项目表
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
public interface ProgramManagerService extends IService<ProgramManagerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 项目保存
     * @param request
     */
    void save(ProgramManagerRequest request);
}

