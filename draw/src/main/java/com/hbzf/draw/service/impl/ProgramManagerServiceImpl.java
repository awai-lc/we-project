package com.hbzf.draw.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ProgramManagerDao;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.service.ProgramManagerService;


@Service("programManagerService")
public class ProgramManagerServiceImpl extends ServiceImpl<ProgramManagerDao, ProgramManagerEntity> implements ProgramManagerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgramManagerEntity> page = this.page(
                new Query<ProgramManagerEntity>().getPage(params),
                new QueryWrapper<ProgramManagerEntity>()
        );

        return new PageUtils(page);
    }

}