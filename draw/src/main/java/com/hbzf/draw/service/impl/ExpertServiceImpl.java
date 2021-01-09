package com.hbzf.draw.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbzf.common.utils.R;
import com.hbzf.draw.dao.MajorDao;
import com.hbzf.draw.entity.MajorEntity;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.enums.JobStatusEnum;
import com.hbzf.draw.enums.UploadResultEnum;
import com.hbzf.draw.util.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;
import com.hbzf.draw.dao.ExpertDao;
import com.hbzf.draw.entity.ExpertEntity;
import com.hbzf.draw.service.ExpertService;


@Slf4j
@Service("expertService")
public class ExpertServiceImpl extends ServiceImpl<ExpertDao, ExpertEntity> implements ExpertService {
    @Autowired
    private MajorDao majorDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper queryWrapper = new QueryWrapper();
        String name = params.get("expertName").toString();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.likeRight("expert_name", name.trim());
        }
        IPage<ExpertEntity> page = this.page(
                new Query<ExpertEntity>().getPage(params),
                queryWrapper
        );
        page.getRecords().forEach((record) -> {
            MajorEntity majorEntity = majorDao.selectById(record.getMajorId());
            if (majorEntity != null) {
                record.setMajorName(majorEntity.getMajorName());
            }
        });
        return new PageUtils(page);
    }

    @Override
    public List<List<String>> batchInsertByLists(List<List<String>> values) {
        if (CollectionUtils.isEmpty(values)) {
            return Lists.newArrayList();
        }
        values.parallelStream().forEach(e -> {
            try {
                ExpertEntity expertEntity = createExpertByList(e);
                //校验是否存在
                if (!checkExit(expertEntity)) {
                    throw new BizException("该专家已经存在");
                }
                save(expertEntity);
                e.add(UploadResultEnum.SUCCESS.getDesc());
            } catch (BizException bizEx) {
                e.add(UploadResultEnum.FAILED.getDesc());
                e.add(bizEx.getMessage());
                log.error("ExpertServiceImpl batchInsertByLists row error row{}", e, bizEx);
            } catch (Exception ex) {
                e.add(UploadResultEnum.FAILED.getDesc());
                e.add("此行数据有误，请仔细核对此行数据!");
                log.error("ExpertServiceImpl batchInsertByLists row error row{}", e, ex);
            }
        });
        return values;
    }

    private boolean checkExit(ExpertEntity expertEntity) {
        return !Objects.nonNull(baseMapper.selectByPhone(expertEntity.getPhone()));
    }

    private ExpertEntity createExpertByList(List<String> e) {
        ExpertEntity expertEntity = new ExpertEntity();
        if (StringUtils.isBlank(e.get(0))) {
            throw new BizException("专家名称不能为空");
        }
        expertEntity.setExpertName(e.get(0));
        if (StringUtils.isBlank(e.get(1))) {
            throw new BizException("专业名称不能为空");
        }
        //查专业
        Map<String, Object> majorMap = Maps.newHashMap();
        majorMap.put("major_name", e.get(1));
        List<MajorEntity> list = majorDao.selectByMap(majorMap);
        if(CollectionUtils.isEmpty(list)){
            throw new BizException("专业不存在");
        }
        MajorEntity majorEntity = (MajorEntity) majorDao.selectByMap(majorMap).get(0);
        expertEntity.setMajorId(majorEntity.getId());
        //手机号
        if (StringUtils.isBlank(e.get(2))) {
            throw new BizException("手机号称不能为空");
        }
        expertEntity.setPhone(e.get(2));

        //身份证
        if (StringUtils.isBlank(e.get(3))) {
            throw new BizException("身份证称不能为空");
        }
        expertEntity.setIdCard(e.get(3));
        //邮箱
        if (StringUtils.isBlank(e.get(4))) {
            throw new BizException("邮箱不能为空");
        }
        expertEntity.setEmail(e.get(4));
        //地址
        if (StringUtils.isBlank(e.get(5))) {
            throw new BizException("地址不能为空");
        }
        expertEntity.setAddress(e.get(5));
        //工作单位
        if (StringUtils.isBlank(e.get(6))) {
            throw new BizException("工作单位不能为空");
        }
        expertEntity.setUnit(e.get(6));
        //在职状态
        if (StringUtils.isBlank(e.get(7))) {
            throw new BizException("在职状态单位不能为空");
        }
        JobStatusEnum jobStatusEnum = JobStatusEnum.parseDesc(e.get(7));
        if(null == jobStatusEnum){
            throw new BizException("在职状态格式错误");
        }
        expertEntity.setJobStatus(jobStatusEnum.getCode());
        //出生年月
        if (StringUtils.isBlank(e.get(8))) {
            throw new BizException("出生年月不能为空");
        }
        expertEntity.setBirth(e.get(8));
        //职称
        if (StringUtils.isBlank(e.get(9))) {
            throw new BizException("职称不能为空");
        }
        expertEntity.setTitle(e.get(9));
        return expertEntity;
    }
}
