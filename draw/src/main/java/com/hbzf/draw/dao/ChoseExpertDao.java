package com.hbzf.draw.dao;

import com.hbzf.draw.entity.ChoseExpertEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 选中专家
 * 
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
@Mapper
public interface ChoseExpertDao extends BaseMapper<ChoseExpertEntity> {

    void deleteByProId(Long id);

    List<ChoseExpertDto> listByProId(Long id);
}
