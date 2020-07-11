package com.hbzf.draw.service.impl;

import com.hbzf.draw.entity.dto.ChoseExpertDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ChoseExpertDao;
import com.hbzf.draw.entity.ChoseExpertEntity;
import com.hbzf.draw.service.ChoseExpertService;

import javax.annotation.Resource;


@Service("choseExpertService")
public class ChoseExpertServiceImpl extends ServiceImpl<ChoseExpertDao, ChoseExpertEntity> implements ChoseExpertService {

    @Resource
    private ChoseExpertDao choseExpertDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChoseExpertEntity> page = this.page(
                new Query<ChoseExpertEntity>().getPage(params),
                new QueryWrapper<ChoseExpertEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ChoseExpertDto> listByProId(Long id) {

        return choseExpertDao.listByProId(id);
    }

}