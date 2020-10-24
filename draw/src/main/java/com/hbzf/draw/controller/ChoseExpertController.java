package com.hbzf.draw.controller;

import com.hbzf.common.utils.PageUtils;
import com.hbzf.common.utils.R;
import com.hbzf.draw.entity.ChoseExpertEntity;
import com.hbzf.draw.entity.ProgramManagerEntity;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import com.hbzf.draw.entity.dto.ProgramManagerDetailDto;
import com.hbzf.draw.entity.request.BuCgridDataRequest;
import com.hbzf.draw.entity.response.ChoseExpertDtoResponse;
import com.hbzf.draw.enums.PurWayEnum;
import com.hbzf.draw.service.ChoseExpertService;
import com.hbzf.draw.service.ProgramManagerService;
import com.hbzf.draw.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



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

    @Autowired
    private ProgramManagerService programManagerService;

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
     * 打印用
     */
    @RequestMapping("/listByProId/{id}")
    //@RequiresPermissions("draw:choseexpert:info")
    public R listByProId(@PathVariable("id") Long id){
        ProgramManagerEntity programManagerEntity = programManagerService.getById(id);
        programManagerEntity.setStartReviewText(DateUtil.formatDateStr(programManagerEntity.getStartReview()));
        programManagerEntity.setEndReviewText(DateUtil.formatDateStr(programManagerEntity.getEndReview()));
        PurWayEnum purWayEnum = PurWayEnum.parseCode(programManagerEntity.getPurWay().intValue());
        if(null != purWayEnum){
            programManagerEntity.setPurWayText(purWayEnum.getDesc());
        }else {
            programManagerEntity.setPurWayText("");
        }
        List<ChoseExpertDto> choseExpert = choseExpertService.listByProId(id);
        //排序，专业一样的放一起
        choseExpert.sort(new Comparator<ChoseExpertDto>() {
            @Override
            public int compare(ChoseExpertDto o1, ChoseExpertDto o2) {
                return o1.getMajorId().compareTo(o2.getMajorId());
            }
        });
        ChoseExpertDtoResponse response = new ChoseExpertDtoResponse();
        response.setProgramManager(programManagerEntity);
        response.setChoseExpertDtoList(choseExpert);
        return R.ok().put("choseExpert", response);
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

    /**
     * 根据传入的 proId 和 phones 查询抽奖人员
     * @param proId 项目id
     * @param phones 手机列表，以英文输入法","分割，如 15811223344,15800000000.13278671342
     * @return 执行完成
     */
    @GetMapping("/lottery")
    public R lottery(@RequestParam(value = "proId") Long proId,
                     @RequestParam(value = "phones") @Nullable String phones) {
        
        return R.ok().put("ExpertDto",choseExpertService.lottery(proId, phones, StringUtils.EMPTY));
    }


    /**
     补抽
     */
    @RequestMapping("/bcLottery")
    public R bcLottery(@RequestBody BuCgridDataRequest request) {

        if(Objects.isNull(request.getProId())){
            return R.ok();
        }
        request.getBuCgridData().removeIf(e -> StringUtils.isBlank(e.getPhone()));
        choseExpertService.bcLottery(request.getProId(), request.getBuCgridData());
        return R.ok();
    }
}
