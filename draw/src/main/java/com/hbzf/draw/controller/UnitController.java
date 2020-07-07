package com.hbzf.draw.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.hbzf.draw.constants.DrawConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzf.draw.entity.UnitEntity;
import com.hbzf.draw.service.UnitService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;


/**
 * 单位
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:02
 */
@RestController
@RequestMapping("draw/unit")
public class UnitController {
    @Autowired
    private UnitService unitService;


    @Autowired(required = false)
    private LoadingCache<String, Map<String, Object>> loadingCache;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:unit:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = unitService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:unit:info")
    public R info(@PathVariable("id") Long id) {
        UnitEntity unit = unitService.getById(id);

        return R.ok().put("unit", unit);
    }


    /**
     * 信息
     */
    @RequestMapping("/queryByName/{name}")
    //@RequiresPermissions("draw:unit:info")
    public R queryByName(@PathVariable("name") String name) {
        return R.ok().put("list", getLikeByMap(name));
    }


    /**
     * Map集合模糊匹配
     *
     * @param keyLike 模糊key
     * @return
     */
    private List<String> getLikeByMap(String keyLike) {
        if(StringUtils.isBlank(keyLike)){
            return Lists.newArrayList();
        }
        List<String> stringList = Lists.newArrayList();
        Map<String, Object> cacheUnchecked = loadingCache.getUnchecked(DrawConstants.PUR_UNIT);
        List<Object> unitList = cacheUnchecked.values().stream().map(e -> e).collect(Collectors.toList());
        for (Object o : unitList) {
            UnitEntity entity = (UnitEntity) o;
            if (entity.getUnitName().contains(keyLike)) {
                stringList.add(entity.getUnitName());
            }

        }
        return stringList;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:unit:save")
    public R save(@RequestBody UnitEntity unit) {
        unitService.save(unit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:unit:update")
    public R update(@RequestBody UnitEntity unit) {
        unitService.updateById(unit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:unit:delete")
    public R delete(@RequestBody Long[] ids) {
        unitService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
