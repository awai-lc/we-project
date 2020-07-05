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

import com.hbzf.draw.entity.PurWayEntity;
import com.hbzf.draw.service.PurWayService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 采购方式
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:57
 */
@RestController
@RequestMapping("draw/purway")
public class PurWayController {
    @Autowired
    private PurWayService purWayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:purway:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purWayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:purway:info")
    public R info(@PathVariable("id") Long id){
		PurWayEntity purWay = purWayService.getById(id);

        return R.ok().put("purWay", purWay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:purway:save")
    public R save(@RequestBody PurWayEntity purWay){
		purWayService.save(purWay);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:purway:update")
    public R update(@RequestBody PurWayEntity purWay){
		purWayService.updateById(purWay);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:purway:delete")
    public R delete(@RequestBody Long[] ids){
		purWayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
