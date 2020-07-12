package com.hbzf.draw.service.impl;

import com.hbzf.draw.entity.dto.ChoseMajorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ChoseMajorDao;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.service.ChoseMajorService;

import javax.annotation.Resource;


@Service("choseMajorService")
public class ChoseMajorServiceImpl extends ServiceImpl<ChoseMajorDao, ChoseMajorEntity> implements ChoseMajorService {

    @Resource
    private ChoseMajorDao choseMajorDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChoseMajorEntity> page = this.page(
                new Query<ChoseMajorEntity>().getPage(params),
                new QueryWrapper<ChoseMajorEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ChoseMajorDto> listByProId(Long proID) {
        return choseMajorDao.listByProId(proID);
    }

}