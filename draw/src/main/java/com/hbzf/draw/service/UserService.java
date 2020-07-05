package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.UserEntity;

import java.util.Map;

/**
 * 登录用户表
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

