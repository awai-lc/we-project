package com.hbzf.draw.dao;

import com.hbzf.draw.entity.ExpertEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbzf.draw.entity.dto.ExpertDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:59
 */
@Mapper
public interface ExpertDao extends BaseMapper<ExpertEntity> {


    List<ExpertDto> listByMajorId(Long majorId);

    ExpertDto selectByPhone(String phone);

    List<String> selectByAllAvoidUnit();

    List<ExpertDto> selectByPhones(@Param("phones") Set<String> phones);
}
