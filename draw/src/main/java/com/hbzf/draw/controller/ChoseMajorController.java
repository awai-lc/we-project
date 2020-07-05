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

import com.hbzf.draw.entity.ChoseMajorEntity;
import com.hbzf.draw.service.ChoseMajorService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 选中专业
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:03:00
 */
@RestController
@RequestMapping("draw/chosemajor")
public class ChoseMajorController {
    @Autowired
    private ChoseMajorService choseMajorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:chosemajor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = choseMajorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:chosemajor:info")
    public R info(@PathVariable("id") Long id){
		ChoseMajorEntity choseMajor = choseMajorService.getById(id);

        return R.ok().put("choseMajor", choseMajor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:chosemajor:save")
    public R save(@RequestBody ChoseMajorEntity choseMajor){
		choseMajorService.save(choseMajor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:chosemajor:update")
    public R update(@RequestBody ChoseMajorEntity choseMajor){
		choseMajorService.updateById(choseMajor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:chosemajor:delete")
    public R delete(@RequestBody Long[] ids){
		choseMajorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
