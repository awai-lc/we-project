package com.hbzf.draw.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbzf.draw.dao.ChoseMajorDao;
import com.hbzf.draw.dao.ExpertDao;
import com.hbzf.draw.dao.ProgramManagerDao;
import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.entity.dto.BuCgridDataDto;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import com.hbzf.draw.entity.dto.ChoseMajorDto;
import com.hbzf.draw.entity.dto.ExpertDto;
import com.hbzf.draw.enums.ProStatusEnum;
import com.hbzf.draw.util.MathUtil;
import com.hbzf.draw.util.SplitterUtil;
import freemarker.template.utility.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private ChoseMajorDao choseMajorDao;

    @Resource
    private ExpertDao expertDao;

    @Resource
    private ProgramManagerDao programManagerDao;

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

    @Override
    public List<ExpertDto> lottery(Long proId, String phones, String avoidExpertPhones) {

        List<ChoseMajorDto> choseMajorDtos = choseMajorDao.listByProId(proId);
        ProgramManagerEntity programManagerEntity = programManagerDao.selectById(proId);
        if (choseMajorDtos == null || programManagerEntity == null) {
            throw new NullPointerException();
        }

        // 专业id，需要的数目
        Map<Long, Integer> map = choseMajorDtos.stream()
                .collect(Collectors.toMap(ChoseMajorDto::getMajorId, ChoseMajorDto::getNeedCount, (old, newOne) -> old));

        // 获取手机列表
        Set<String> phoneList = null;
        if (phones != null && phones.trim().length() > 0) {
            phoneList = Arrays.stream(phones.split(","))
                    .map(String::trim)
                    .collect(Collectors.toSet());
        }
        List<ExpertDto> experts = this.getExpertWithPhone(map, phoneList,
                Optional.ofNullable(programManagerEntity.getAvoidUnit()).orElse(StringUtils.EMPTY), avoidExpertPhones);


        //删除上一次抽取结果
        choseExpertDao.deleteByProId(proId);

        // 将查询结果插入表中
        ChoseExpertEntity entity;
        for (ExpertDto expert : experts) {
            entity = new ChoseExpertEntity();
            entity.setExpertId(expert.getExpertId());
            entity.setMajorId(expert.getMajorId());
            entity.setProId(proId);

            for (ChoseMajorDto d : choseMajorDtos) {
                if (d.getMajorId().equals(expert.getMajorId())) {
                    entity.setIsDelete(d.getIsDelete());
                }
            }
            entity.setAddby(expert.getAddby());
            entity.setAddbytime(expert.getAddbytime());
            entity.setLastmodifiedby(expert.getLastmodifiedby());
            entity.setLastmodifiedbytime(expert.getLastmodifiedbytime());
            choseExpertDao.insert(entity);
        }


        ProgramManagerEntity update = new ProgramManagerEntity();
        update.setId(proId);
        update.setProStatus(ProStatusEnum.WC.getCode());
        programManagerDao.updateById(update);
        return experts;
    }

    @Override
    public void bcLottery(Long proId, List<BuCgridDataDto> buCgridData) {
        if (CollectionUtils.isEmpty(buCgridData)) {
            return;
        }
        List<ChoseExpertDto> choseExpertDtoList = listByProId(proId);
        if (CollectionUtils.isEmpty(choseExpertDtoList)) {
            return;
        }
        List<String> phones = buCgridData.stream().map(BuCgridDataDto::getPhone).collect(Collectors.toList());
        List<ExpertDto> expertDtos = expertDao.selectByPhones(new HashSet<>(phones));
        if (CollectionUtils.isEmpty(expertDtos)) {
            return;
        }
        List<ChoseMajorDto> choseMajorDtos = choseMajorDao.listByProId(proId);
        // 专业id，需要的数目
        Map<Long, Integer> map = choseMajorDtos.stream()
                .collect(Collectors.toMap(ChoseMajorDto::getMajorId, ChoseMajorDto::getNeedCount, (old, newOne) -> old));
        Set<Long> choseMajorKey = map.keySet();
        //选择出需要移除的专家
        expertDtos.removeIf(e-> !choseMajorKey.contains(e.getMajorId()));

        //选择出需要保留的专家
        List<Long> needCancelExpertIds = expertDtos.stream().map(ExpertDto::getExpertId).collect(Collectors.toList());
        choseExpertDtoList.removeIf(e -> needCancelExpertIds.contains(e.getExpertId()));

        String needChosePhones = SplitterUtil.longListToStrWithComma(choseExpertDtoList.stream().map(ChoseExpertDto::getPhone).collect(Collectors.toList()));
        String needAvoidPhones = SplitterUtil.longListToStrWithComma(expertDtos.stream().map(ExpertDto::getPhone).collect(Collectors.toList()));
        //抽取
        lottery(proId, needChosePhones, needAvoidPhones);

    }

    private void deteleCancleExpert(List<ExpertDto> expertDtos, Long proId) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("pro_id", proId);
        updateWrapper.in("expert_id", expertDtos.stream().map(ExpertDto::getExpertId).collect(Collectors.toList()));
        updateWrapper.eq("is_delete", 0);

        ChoseExpertEntity entity = new ChoseExpertEntity();
        entity.setIsDelete(1);
        update(entity, updateWrapper);
    }

    /**
     * 如果 phone_list 为空：
     * 则每个 major_id 随机抽取 need_cound 个专家，存入 chose_export 表，结束;
     * 如果 phone_list 不为空：
     * 则抽中所有 phone 的专家，并且相对应 major_id 的剩余抽取专家数减 1，
     * 抽取剩余专家
     *
     * @param map    存储 majorId 和 needConut
     * @param phones 手机号列表
     * @return
     * @Author harryper
     * @Date 2020/7/12 17:53
     **/
    public List<ExpertDto> getExpertWithPhone(Map<Long, Integer> map, Set<String> phones, String avoidUnit, String avoidExpertPhones) {
        Map<Long, List<Long>> needAvoidExpertMap = Maps.newHashMap();
        Set<Long> marjorIds = map.keySet();
        // 手机号不为空
        ArrayList<ExpertDto> returnDto = new ArrayList<>();
        if (phones != null && phones.size() > 0) {
            List<ExpertDto> dtos = expertDao.selectByPhones(phones);
            for (ExpertDto dto : dtos) {
                //输入手机号不在选中专业中或者专家为回避专家单位
                if (!marjorIds.contains(dto.getMajorId()) || avoidUnit.contains(dto.getUnit())) {
                    continue;
                }
                // 将查询到的专家存入返回结果
                returnDto.add(dto);
                // 将对应 majorId 的 needCount 数目减 1
                map.put(dto.getMajorId(), map.get(dto.getMajorId()) - 1);
                if (needAvoidExpertMap.get(dto.getMajorId()) != null) {
                    needAvoidExpertMap.get(dto.getMajorId()).add(dto.getExpertId());
                } else {
                    List<Long> needAvoidUnit = Lists.newArrayList(dto.getExpertId());
                    needAvoidExpertMap.put(dto.getMajorId(), needAvoidUnit);
                }
            }
        }
        returnDto.addAll(this.getExpert(map, needAvoidExpertMap, avoidUnit, avoidExpertPhones));
        return returnDto;
    }


    /**
     * 抽取专家
     *
     * @param map <majorId, needCount> 需要 nendCount 个 majorId 的专家
     * @return
     * @Author harryper
     * @Date 2020/7/12 18:16
     **/
    public List<ExpertDto> getExpert(Map<Long, Integer> map, Map<Long, List<Long>> avoidExpertMap, String avoidUnit, String avoidExpertPhones) {
        List<ExpertDto> returnDto = new ArrayList<>();
        Set<Long> keys = map.keySet();
        for (Long key : keys) {
            List<ExpertDto> expertDtos = expertDao.listByMajorId(key);
            // 如果不存在专家
            if (expertDtos == null || expertDtos.size() <= 0) {
                continue;
            }
            List<Long> avoidUnitIds = avoidExpertMap.get(key);
            expertDtos.removeIf(e -> Optional.ofNullable(avoidUnitIds).orElse(Lists.newArrayList()).contains(e.getExpertId()));
            //去除回避专家单位的专家
            expertDtos.removeIf(e -> avoidUnit.contains(e.getUnit()));
            //去除需回避专家
            expertDtos.removeIf(e -> avoidExpertPhones.contains(e.getPhone()));
            // 如果总的专家数目小于等于要抽取的，则不需抽取直接添加
            if (expertDtos.size() <= map.get(key)) {
                returnDto.addAll(expertDtos);
                continue;
            }
            // 总的专家数目大于要抽取的，则随机抽取 needCount 个
            int[] needCount = MathUtil.randomNum(1, expertDtos.size() + 1, map.get(key));
            for (int value : needCount) {
                returnDto.add(expertDtos.get(value - 1));
            }
        }
        return returnDto;
    }


}
