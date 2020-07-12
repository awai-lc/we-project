package com.hbzf.draw.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author harryper
 * @description 需要操作数字等的类
 * @date 2020/7/12
 */
public class MathUtil {


    /**
     * 随机指定范围内N个不重复的数
     *
     * @param min 指定范围最小值(不能为0)
     * @param max 指定范围最大值(不包括)
     * @param n   随机数个数
     */
    public static int[] randomNum(int min, int max, int n) {
        if (n >= (max - min + 1) || max <= min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
