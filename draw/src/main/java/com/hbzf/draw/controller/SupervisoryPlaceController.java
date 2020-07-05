package com.hbzf.draw.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzf.draw.entity.SupervisoryPlaceEntity;
import com.hbzf.draw.service.SupervisoryPlaceService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 监管地
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
@RestController
@RequestMapping("draw/supervisoryplace")
public class SupervisoryPlaceController {
    @Autowired
    private SupervisoryPlaceService supervisoryPlaceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:supervisoryplace:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = supervisoryPlaceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:supervisoryplace:info")
    public R info(@PathVariable("id") Long id){
		SupervisoryPlaceEntity supervisoryPlace = supervisoryPlaceService.getById(id);

        return R.ok().put("supervisoryPlace", supervisoryPlace);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:supervisoryplace:save")
    public R save(@RequestBody SupervisoryPlaceEntity supervisoryPlace){
		supervisoryPlaceService.save(supervisoryPlace);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:supervisoryplace:update")
    public R update(@RequestBody SupervisoryPlaceEntity supervisoryPlace){
		supervisoryPlaceService.updateById(supervisoryPlace);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:supervisoryplace:delete")
    public R delete(@RequestBody Long[] ids){
		supervisoryPlaceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
