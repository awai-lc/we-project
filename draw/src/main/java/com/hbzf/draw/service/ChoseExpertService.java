package com.hbzf.draw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hbzf.common.utils.PageUtils;
import com.hbzf.draw.entity.ChoseExpertEntity;
import com.hbzf.draw.entity.dto.BuCgridDataDto;
import com.hbzf.draw.entity.dto.ChoseExpertDto;
import com.hbzf.draw.entity.dto.ExpertDto;

import java.util.List;
import java.util.Map;

/**
 * 选中专家
 *
 * @author liucheng
 * @email 36628353@qq.com
 * @date 2020-07-05 00:02:58
 */
public interface ChoseExpertService extends IService<ChoseExpertEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ChoseExpertDto> listByProId(Long id);

    /**
     * 抽奖
     * @Author harryper
     * @Date 2020/7/12 12:13
     * @param
     * @return
     **/
    List<ExpertDto> lottery(Long proId, String phones, String avoidExpertPhones);

    void bcLottery(Long proId, List<BuCgridDataDto> buCgridData);
}

