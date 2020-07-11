package com.hbzf.draw.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.hbzf.draw.entity.ExpertEntity;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzf.draw.entity.ChoseExpertEntity;
import com.hbzf.draw.service.ChoseExpertService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;



/**
 * 选中专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
@RestController
@RequestMapping("draw/choseexpert")
public class ChoseExpertController {
    @Autowired
    private ChoseExpertService choseExpertService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:choseexpert:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = choseExpertService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:choseexpert:info")
    public R info(@PathVariable("id") Long id){
		ChoseExpertEntity choseExpert = choseExpertService.getById(id);

        return R.ok().put("choseExpert", choseExpert);
    }

    /**
     * 信息
     */
    @RequestMapping("/listByProId/{id}")
    //@RequiresPermissions("draw:choseexpert:info")
    public R listByProId(@PathVariable("id") Long id){
        List<ChoseExpertDto> choseExpert = choseExpertService.listByProId(id);

        return R.ok().put("choseExpert", choseExpert);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:choseexpert:save")
    public R save(@RequestBody ChoseExpertEntity choseExpert){
		choseExpertService.save(choseExpert);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:choseexpert:update")
    public R update(@RequestBody ChoseExpertEntity choseExpert){
		choseExpertService.updateById(choseExpert);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:choseexpert:delete")
    public R delete(@RequestBody Long[] ids){
		choseExpertService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
