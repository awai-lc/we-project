package com.hbzf.draw.dao;

import com.hbzf.draw.entity.ChoseMajorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbzf.draw.entity.dto.ChoseMajorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 选中专业
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:00
 */
@Mapper
public interface ChoseMajorDao extends BaseMapper<ChoseMajorEntity> {

    void deleteByProId(Long proId);

    /**
     * 根据 proId 选择专业
     * @param proId
     * @return
     */
    List<ChoseMajorDto> listByProId(Long proId);
}
