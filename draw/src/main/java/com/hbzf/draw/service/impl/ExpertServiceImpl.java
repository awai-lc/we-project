package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ExpertDao;
import com.hbzf.draw.entity.ExpertEntity;
import com.hbzf.draw.service.ExpertService;


@Service("expertService")
public class ExpertServiceImpl extends ServiceImpl<ExpertDao, ExpertEntity> implements ExpertService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExpertEntity> page = this.page(
                new Query<ExpertEntity>().getPage(params),
                new QueryWrapper<ExpertEntity>()
        );

        return new PageUtils(page);
    }

}