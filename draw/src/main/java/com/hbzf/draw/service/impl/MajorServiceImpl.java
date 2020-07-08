package com.hbzf.draw.service.impl;

import com.hbzf.draw.dao.ExpertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.Query;

import com.hbzf.draw.dao.MajorDao;
import com.hbzf.draw.entity.MajorEntity;
import com.hbzf.draw.service.MajorService;
import org.springframework.util.StringUtils;


@Service("majorService")
public class MajorServiceImpl extends ServiceImpl<MajorDao, MajorEntity> implements MajorService {
    @Autowired
    private ExpertDao expertDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MajorEntity> page = this.page(
                new Query<MajorEntity>().getPage(params),
                new QueryWrapper<MajorEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, long parentId) {

        List<MajorEntity> entityList = baseMapper.selectList(null);
        //根据parentId查询子节点
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((obj) -> {
                obj.like("code", key).or().like("major_name", key);
            });
        }

        IPage<MajorEntity> page = this.page(
                new Query<MajorEntity>().getPage(params),
                queryWrapper
        );
        //当点击的是叶子节点，查询本身专家数
        if (page.getRecords().size() == 0){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id", parentId);
            IPage<MajorEntity> majorpage = this.page(
                    new Query<MajorEntity>().getPage(new HashMap<String, Object>()),
                    wrapper
            );
            majorpage.getRecords().forEach((record)->{
                QueryWrapper wra = new QueryWrapper();
                wra.eq("major_id", record.getId());
                record.setExpertCount(expertDao.selectCount(wra));
            });
            return new PageUtils(majorpage);
        }

        //获取分页查询的数据（子节点数据）,并做封装
        List<MajorEntity> collect = page.getRecords().stream().map((record) -> {
            record.setExpertCount(getExpertCount(record, entityList, 0));
            return record;
        }).collect(Collectors.toList());
        page.setRecords(collect);
        return new PageUtils(page);

    }
    //递归获取专家数
    private int getExpertCount(MajorEntity record, List<MajorEntity> entityList, int count) {
        //先查询本身节点挂载的专家数
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("major_id", record.getId());
        Integer reccnt = expertDao.selectCount(wrapper);
        count = count + reccnt;

        List<MajorEntity> collect = entityList.stream().filter((entity) -> {
            return entity.getParentId() == record.getId();
        }).collect(Collectors.toList());

        for (MajorEntity majorEntity : collect) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("major_id", majorEntity.getId());
            Integer cnt = expertDao.selectCount(queryWrapper);
            count = count + cnt;
            getExpertCount(majorEntity, entityList, count);
        }
        return count;
    }

    @Override
    public List<MajorEntity> listWithTree() {
        List<MajorEntity> majorEntityList = baseMapper.selectList(null);
        //找出所有的父节点
        Stream<MajorEntity> majorEntityStream = majorEntityList.stream().filter((majorEntity) -> {
            return majorEntity.getParentId() == 0;
        });
        List<MajorEntity> collect = majorEntityStream.map((major) -> {
            major.setChildMajor(getChild(major, majorEntityList));
            return major;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Long[] getPath(Long majorId) {
        List<Long> pathList = new ArrayList<>();
        List<Long> path = findParentPath(majorId,pathList);
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }

    private List<Long> findParentPath(Long majorId, List<Long> pathList) {
        MajorEntity majorEntity = baseMapper.selectById(majorId);
        pathList.add(majorEntity.getId());
        if (majorEntity.getParentId()!=0) {
            findParentPath(majorEntity.getParentId(), pathList);
        }
        return pathList;
    }

    public List<MajorEntity> getChild(MajorEntity root, List<MajorEntity> list) {
        List<MajorEntity> collect = list.stream().filter((major) -> {
            return major.getParentId() == root.getId();
        }).map((major) -> {
            major.setChildMajor(getChild(major, list));
            return major;
        }).collect(Collectors.toList());
        return collect;
    }

}