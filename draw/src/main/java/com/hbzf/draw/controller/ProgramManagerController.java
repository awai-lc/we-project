package com.hbzf.draw.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.JSON;
import com.hbzf.draw.entity.request.ProgramManagerRequest;
import com.hbzf.draw.util.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.service.ProgramManagerService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;


/**
 * 项目表
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
@Slf4j
@RestController
@RequestMapping("draw/programmanager")
public class ProgramManagerController {
    @Autowired
    private ProgramManagerService programManagerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("draw:programmanager:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = programManagerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("draw:programmanager:info")
    public R info(@PathVariable("id") Long id) {
        ProgramManagerEntity programManager = programManagerService.getById(id);

        return R.ok().put("programManager", programManager);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("draw:programmanager:save")
    public R save(@RequestBody ProgramManagerRequest request) {
        try {
            if (null == request || null == request.getProgramManager()) {
                return R.error(R.ILLEGAL_ARGUMENT_CODE, R.ILLEGAL_ARGUMENT_MESSAGE);
            }
            return R.ok(programManagerService.save(request).toString());
        } catch (BizException e) {
            return R.error(e.getMessage());
        } catch (Exception e) {
            log.error("ProgramManagerController save request={}", JSON.toJSONString(request));
            return R.error(R.ERROR_CODE, R.ERROR_MESSAGE);
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("draw:programmanager:update")
    public R update(@RequestBody ProgramManagerEntity programManager) {
        programManagerService.updateById(programManager);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("draw:programmanager:delete")
    public R delete(@RequestBody Long[] ids) {
        programManagerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
