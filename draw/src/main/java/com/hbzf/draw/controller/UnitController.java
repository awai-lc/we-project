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

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:unit:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = unitService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:unit:info")
    public R info(@PathVariable("id") Long id){
		UnitEntity unit = unitService.getById(id);

        return R.ok().put("unit", unit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:unit:save")
    public R save(@RequestBody UnitEntity unit){
		unitService.save(unit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:unit:update")
    public R update(@RequestBody UnitEntity unit){
		unitService.updateById(unit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:unit:delete")
    public R delete(@RequestBody Long[] ids){
		unitService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
