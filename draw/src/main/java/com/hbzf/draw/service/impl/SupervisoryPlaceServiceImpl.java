package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.SupervisoryPlaceDao;
import com.hbzf.draw.entity.SupervisoryPlaceEntity;
import com.hbzf.draw.service.SupervisoryPlaceService;


@Service("supervisoryPlaceService")
public class SupervisoryPlaceServiceImpl extends ServiceImpl<SupervisoryPlaceDao, SupervisoryPlaceEntity> implements SupervisoryPlaceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SupervisoryPlaceEntity> page = this.page(
                new Query<SupervisoryPlaceEntity>().getPage(params),
                new QueryWrapper<SupervisoryPlaceEntity>()
        );

        return new PageUtils(page);
    }

}