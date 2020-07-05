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

import com.hbzf.draw.entity.ExpertEntity;
import com.hbzf.draw.service.ExpertService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:59
 */
@RestController
@RequestMapping("draw/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:expert:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = expertService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:expert:info")
    public R info(@PathVariable("id") Long id){
		ExpertEntity expert = expertService.getById(id);

        return R.ok().put("expert", expert);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:expert:save")
    public R save(@RequestBody ExpertEntity expert){
		expertService.save(expert);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:expert:update")
    public R update(@RequestBody ExpertEntity expert){
		expertService.updateById(expert);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:expert:delete")
    public R delete(@RequestBody Long[] ids){
		expertService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
