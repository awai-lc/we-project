package com.hbzf.draw.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SplitterUtil {
    private static final String COMMA = ",";

    /**
     * str转List<Long>
     *
     * @param str
     * @param separation 分隔符
     * @return
     */
    private static List<Long> strToLongList(String str, String separation) {
        Splitter splitter = Splitter.on(separation).omitEmptyStrings().trimResults();
        return splitter.splitToList(str).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    }

    public static List<Long> strToLongListWithComma(String str) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return strToLongList(str, COMMA);
    }

    public static String longListToStrWithComma(List<String> list) {

        return Joiner.on(",").join(list);
    }

}
