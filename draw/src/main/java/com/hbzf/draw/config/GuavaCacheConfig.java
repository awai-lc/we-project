package com.hbzf.draw.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.hbzf.draw.constants.DrawConstants;
import com.hbzf.draw.dao.PurWayDao;
import com.hbzf.draw.dao.SupervisoryPlaceDao;
import com.hbzf.draw.dao.UnitDao;
import com.hbzf.draw.entity.UnitEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 少凡李
 * 本地缓存
 */
@Slf4j
@Configuration
public class GuavaCacheConfig {

    @Autowired(required = false)
    private PurWayDao purWayMapper;

    @Autowired(required = false)
    private UnitDao unitMapper;

    @Autowired(required = false)
    private SupervisoryPlaceDao supervisoryPlaceMapper;

    @Bean(name = "loadingCache")
    public LoadingCache<String, Map<String, Object>> getLoadingCache() {
        return CacheBuilder.newBuilder().maximumSize(4).
                expireAfterWrite(2, TimeUnit.MINUTES).
                build(new CacheLoader<String, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> load(String key) {
                        Map<String, Object> map = new HashMap<>();
                        switch (key) {

                            case DrawConstants.PUR_UNIT:
                                List<UnitEntity> unitList = unitMapper.selectByAll();
                                if (CollectionUtils.isNotEmpty(unitList)) {
                                    unitList.forEach(unit -> {
                                        map.put(unit.getId().toString(), unit);
                                    });
                                }
                                break;
                            default:
                                break;
                        }
                        return map;
                    }
                });
    }

}
