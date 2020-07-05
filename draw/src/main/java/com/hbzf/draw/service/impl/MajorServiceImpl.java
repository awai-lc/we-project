package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.MajorDao;
import com.hbzf.draw.entity.MajorEntity;
import com.hbzf.draw.service.MajorService;


@Service("majorService")
public class MajorServiceImpl extends ServiceImpl<MajorDao, MajorEntity> implements MajorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MajorEntity> page = this.page(
                new Query<MajorEntity>().getPage(params),
                new QueryWrapper<MajorEntity>()
        );

        return new PageUtils(page);
    }

}