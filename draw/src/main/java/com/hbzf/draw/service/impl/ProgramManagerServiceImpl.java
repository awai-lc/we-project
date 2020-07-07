package com.hbzf.draw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;
import com.hbzf.draw.dao.ChoseMajorDao;
import com.hbzf.draw.dao.ProgramManagerDao;
import com.hbzf.draw.dao.biz.UnitBiz;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.entity.request.ProgramManagerRequest;
import com.hbzf.draw.enums.ProStatusEnum;
import com.hbzf.draw.enums.PurWayEnum;
import com.hbzf.draw.enums.SupervisoryPlaceEnum;
import com.hbzf.draw.service.ProgramManagerService;
import com.hbzf.draw.util.DateUtil;
import com.hbzf.draw.util.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service("programManagerService")
public class ProgramManagerServiceImpl extends ServiceImpl<ProgramManagerDao, ProgramManagerEntity> implements ProgramManagerService {

    @Resource
    private ChoseMajorDao choseMajorDao;
    @Resource
    private UnitBiz unitBiz;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgramManagerEntity> page = this.page(
                new Query<ProgramManagerEntity>().getPage(params),
                new QueryWrapper<ProgramManagerEntity>()
        );

        transToProDto(page.getRecords());
        return new PageUtils(page);
    }

    private void transToProDto(List<ProgramManagerEntity> page) {
        if(CollectionUtils.isNotEmpty(page)){
            page.forEach(e ->{
                ProStatusEnum statusEnum = ProStatusEnum.parseCode(e.getProStatus());
                if(Objects.nonNull(statusEnum)){
                    e.setProStatusText(statusEnum.getDesc());
                }
                PurWayEnum purWayEnum = PurWayEnum.parseCode(e.getPurWay().intValue());
                if(Objects.nonNull(purWayEnum)){
                    e.setPurWayText(purWayEnum.getDesc());
                }
                SupervisoryPlaceEnum placeEnum = SupervisoryPlaceEnum.parseCode(e.getSupervisoryPlaceId().intValue());
                if(Objects.nonNull(placeEnum)){
                    e.setSupervisoryPlaceText(placeEnum.getDesc());
                }
                e.setStartReviewText(DateUtil.formatDateStr(e.getStartReview()));
                e.setEndReviewText(DateUtil.formatDateStr(e.getEndReview()));
            });

        }
    }

    @Override
    public Long save(ProgramManagerRequest request) {
        if (null == request.getProgramManager().getId()) {
            insertManager(request);
        } else {
            updateManager(request);
        }
        //判断采购单位是否存在不存在则插入
        try {
            unitBiz.insertOrUpdate(request.getProgramManager().getPurchasingId());
        } catch (Exception e) {
            log.error("unitBiz.insertOrUpdate error PurchasingId{}", request.getProgramManager().getPurchasingId(), e);
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
        choseMajorList.parallelStream().forEach(e -> {
            choseMajorDao.insert(e);
        });
    }

    private List<ChoseMajorEntity> transToChoseMajor(ProgramManagerRequest request) {
        List<ChoseMajorEntity> entityList = Lists.newArrayList();
        request.getMajorList().forEach(e -> {
            ChoseMajorEntity entity = new ChoseMajorEntity();
            BeanUtils.copyProperties(e, entity);
            entity.setProId(request.getProgramManager().getId());
            entityList.add(entity);
        });
        return entityList;
    }


    private void insertManager(ProgramManagerRequest request) {
        //校验name是否重复
        if (Objects.nonNull(getByName(request.getProgramManager().getName()))) {
            throw new BizException("已存在名称为" + request.getProgramManager().getName() + "的项目");
        }
        ProgramManagerEntity manager = transToProgramManager(request);
        save(manager);
        request.getProgramManager().setId(manager.getId());
        insertMajorList(request);
    }

    public ProgramManagerEntity getByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", name);
        return getOne(queryWrapper);
    }

    private ProgramManagerEntity transToProgramManager(ProgramManagerRequest request) {
        ProgramManagerEntity entity = new ProgramManagerEntity();
        BeanUtils.copyProperties(request.getProgramManager(), entity);
        entity.setCode(createProCode());
        entity.setProStatus(ProStatusEnum.D.getCode());
        //todo 操作人
        entity.setAddby(1L);
        entity.setAddbytime(new Date());
        entity.setLastmodifiedby(1L);
        entity.setLastmodifiedbytime(new Date());
        return entity;
    }

    private String createProCode() {
        return "PR-" + new Date().toString();
    }


}