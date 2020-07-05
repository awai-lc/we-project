package com.hbzf.draw.dao;

import com.hbzf.draw.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录用户表
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
