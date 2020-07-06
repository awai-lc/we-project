package com.hbzf.draw.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.google.common.collect.Lists;
import com.hbzf.draw.dao.ChoseMajorDao;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.entity.dto.ProgramManagerDto;
import com.hbzf.draw.entity.request.ProgramManagerRequest;
import com.hbzf.draw.enums.ProStatusEnum;
import com.hbzf.draw.util.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.ProgramManagerDao;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.service.ProgramManagerService;

import javax.annotation.Resource;


@Service("programManagerService")
public class ProgramManagerServiceImpl extends ServiceImpl<ProgramManagerDao, ProgramManagerEntity> implements ProgramManagerService {

    @Resource
    private ChoseMajorDao choseMajorDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgramManagerEntity> page = this.page(
                new Query<ProgramManagerEntity>().getPage(params),
                new QueryWrapper<ProgramManagerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long save(ProgramManagerRequest request) {
        if(null == request.getProgramManager().getId()){
            insertManager(request);
        }else {
            updateManager(request);
        }
        return request.getProgramManager().getId();
    }


    private void updateManager(ProgramManagerRequest request) {
        ProgramManagerEntity manager = transToProgramManager(request);
        updateById(manager);
        insertMajorList(request);
    }

    private void insertMajorList(ProgramManagerRequest request) {
        List<ChoseMajorEntity> choseMajorList = transToChoseMajor(request);
        choseMajorDao.deleteByProId(request.getProgramManager().getId());
        choseMajorList.parallelStream().forEach(e ->{
            choseMajorDao.insert(e);
        });
    }

    private List<ChoseMajorEntity> transToChoseMajor(ProgramManagerRequest request) {
        List<ChoseMajorEntity> entityList = Lists.newArrayList();
        request.getMajorList().forEach(e ->{
            ChoseMajorEntity entity = new ChoseMajorEntity();
            BeanUtils.copyProperties(e, entity);
            entity.setProId(request.getProgramManager().getId());
            entityList.add(entity);
        });
        return entityList;
    }


    private void insertManager(ProgramManagerRequest request) {
        //校验name是否重复
        if(Objects.nonNull(getByName(request.getProgramManager().getName()))){
            throw new BizException("已存在名称为"+ request.getProgramManager().getName() + "的项目");
        }
        ProgramManagerEntity manager = transToProgramManager(request);
        save(manager);
        request.getProgramManager().setId(manager.getId());
        insertMajorList(request);
    }

    public ProgramManagerEntity getByName(String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", name);
       return getOne(queryWrapper);
    }

    private ProgramManagerEntity transToProgramManager(ProgramManagerRequest request) {
        ProgramManagerEntity entity = new ProgramManagerEntity();
        BeanUtils.copyProperties(request.getProgramManager(), entity);
        entity.setCode(UUID.randomUUID().toString());
        entity.setProStatus(ProStatusEnum.D.getCode());
        //todo 操作人
        entity.setAddby(1L);
        entity.setAddbytime(new Date());
        entity.setLastmodifiedby(1L);
        entity.setLastmodifiedbytime(new Date());
        return entity;
    }

}