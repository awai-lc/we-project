package com.hbzf.draw.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author yaodonghao
 * @since 2020/5/5 上午
 */
public class PageUtil {
    public static <F, T> IPage<T> convert(IPage<F> page, List<T> newRecords) {
        IPage<T> toPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        toPage.setRecords(newRecords);
        return toPage;
    }
}
