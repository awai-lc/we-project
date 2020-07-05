package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.PurWayDao;
import com.hbzf.draw.entity.PurWayEntity;
import com.hbzf.draw.service.PurWayService;


@Service("purWayService")
public class PurWayServiceImpl extends ServiceImpl<PurWayDao, PurWayEntity> implements PurWayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurWayEntity> page = this.page(
                new Query<PurWayEntity>().getPage(params),
                new QueryWrapper<PurWayEntity>()
        );

        return new PageUtils(page);
    }

}