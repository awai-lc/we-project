package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.UnitDao;
import com.hbzf.draw.entity.UnitEntity;
import com.hbzf.draw.service.UnitService;


@Service("unitService")
public class UnitServiceImpl extends ServiceImpl<UnitDao, UnitEntity> implements UnitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UnitEntity> page = this.page(
                new Query<UnitEntity>().getPage(params),
                new QueryWrapper<UnitEntity>()
        );

        return new PageUtils(page);
    }

}