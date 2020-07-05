package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ChoseMajorDao;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.service.ChoseMajorService;


@Service("choseMajorService")
public class ChoseMajorServiceImpl extends ServiceImpl<ChoseMajorDao, ChoseMajorEntity> implements ChoseMajorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChoseMajorEntity> page = this.page(
                new Query<ChoseMajorEntity>().getPage(params),
                new QueryWrapper<ChoseMajorEntity>()
        );

        return new PageUtils(page);
    }

}