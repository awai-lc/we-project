package com.hbzf.draw.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzf.draw.entity.MajorEntity;
import com.hbzf.draw.service.MajorService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 专业
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:01
 */
@RestController
@RequestMapping("draw/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    /**
     * 根据id查询子专业列表
     */
    @RequestMapping("/list/{id}")
    //@RequiresPermissions("draw:major:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("id") long id){

        PageUtils page = majorService.queryPage(params, id);

        return R.ok().put("page", page);
    }
    /**
     * 树状列表
     */
    @RequestMapping("/list/tree")
    //@RequiresPermissions("draw:major:list")
    public R listTree(){
        List<MajorEntity> majorEntityList =  majorService.listWithTree();

        return R.ok().put("data", majorEntityList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:major:info")
    public R info(@PathVariable("id") Long id){
		MajorEntity major = majorService.getById(id);

        return R.ok().put("major", major);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:major:save")
    public R save(@RequestBody MajorEntity major){
		majorService.save(major);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:major:update")
    public R update(@RequestBody MajorEntity major){
		majorService.updateById(major);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:major:delete")
    public R delete(@RequestBody Long[] ids){
		majorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
