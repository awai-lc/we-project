package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ChoseExpertDao;
import com.hbzf.draw.entity.ChoseExpertEntity;
import com.hbzf.draw.service.ChoseExpertService;


@Service("choseExpertService")
public class ChoseExpertServiceImpl extends ServiceImpl<ChoseExpertDao, ChoseExpertEntity> implements ChoseExpertService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChoseExpertEntity> page = this.page(
                new Query<ChoseExpertEntity>().getPage(params),
                new QueryWrapper<ChoseExpertEntity>()
        );

        return new PageUtils(page);
    }

}