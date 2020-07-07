package com.hbzf.draw.dao.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.cache.LoadingCache;
import com.hbzf.draw.constants.DrawConstants;
import com.hbzf.draw.dao.UnitDao;
import com.hbzf.draw.entity.UnitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * 单位复用层
 */
@Service
public class UnitBiz {

    @Resource
    private UnitDao unitDao;

    @Autowired(required = false)
    private LoadingCache<String, Map<String, Object>> loadingCache;

    public UnitEntity selectByName(String purchasingId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("unit_name", purchasingId);
        return unitDao.selectOne(queryWrapper);
    }

    public void insertOrUpdate(String purchasingId) {
        UnitEntity queryEntity = selectByName(purchasingId);
        if(Objects.isNull(queryEntity)){
            UnitEntity unitEntity = new UnitEntity();
            unitEntity.setUnitName(purchasingId);
            unitDao.insert(unitEntity);
            loadingCache.refresh(DrawConstants.PUR_UNIT);
        }
    }
}
