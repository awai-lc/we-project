package com.hbzf.draw.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author luosai
 * @date 2020/5/10
 */
@Slf4j
public class JsonUtil {
    private final static String TIME_ZERO = "GMT+8";
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ObjectMapper objectMapper;

    static {

        objectMapper = new ObjectMapper();
        // 去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone(TIME_ZERO));
        // 空值不序列化，只对pojo起作用，对map和list不起作用
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 空bean不报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化时，属性不存在的兼容处理（未知属性是否抛出异常）
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号处理,该特性决定是否允许单引号来包住属性名称和字符串值。
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 对象转换为 JSON 字符串 忽略空值
     * obj 为空时返回 "null"
     */
    public static String obj2Json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtil.obj2json error", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 转换为 JavaBean
     *
     * @param jsonString 参数为""或者为null 时返回null
     */
    public static <T> T json2Pojo(String jsonString, Class<T> clazz) {

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.error("JsonUtil.json2pojo error", e);
        }
        return null;
    }

    /**
     * 字符串转换为 Map<String, Object>
     */
    @SuppressWarnings(value = {"unchecked"})
    public static <T> Map<String, Object> json2Map(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            log.error("JsonUtil.json2pojo error", e);
        }
        return Maps.newHashMap();
    }

    /**
     * 字符串转换为 Map<String, T>
     */
    public static <T> Map<String, T> json2Map(String jsonString, Class<T> clazz) {
        Map<String, T> result = Maps.newHashMap();
        try {
            Map<String, Map<String, T>> map =
                    objectMapper.readValue(jsonString, new TypeReference<Map<String, Map<String, T>>>() {
                    });

            for (Map.Entry<String, Map<String, T>> entry : map.entrySet()) {
                result.put(entry.getKey(), map2Pojo(entry.getValue(), clazz));
            }
        } catch (Exception e) {
            log.error("JsonUtil.json2mapClazz error ", e);
        }
        return result;
    }

    /**
     * 将 JSON 数组转换为List集合
     */
    public static <T> List<T> json2List(String jsonArrayStr, Class<T> clazz) {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(jsonArrayStr, javaType);
        } catch (Exception e) {
            log.error("JsonUtil.json2list error", e);
        }
        return Lists.newArrayList();
    }


    /**
     * 泛型反序列化
     */
    public static <T> T parseObject(String jsonStr, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (Exception e) {
            log.error("JsonUtil.parseObject error", e);
        }
        return null;
    }

    /**
     * 获取泛型的 Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    @SuppressWarnings("SameParameterValue")
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json传转JsonNode
     * @param jsonString
     * @return
     */
    public static JsonNode json2JsonNode(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.error("JsonUtil.json2JsonNode error", e);
        }
        return null;
    }
    /**
     * 将 Map 转换为 JavaBean
     *
     * @param map
     * @param clazz
     * @return
     */
    @SuppressWarnings(value = {"all"})
    private static <T> T map2Pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static boolean isJsonArray(String jsonStr) {
        return StringUtils.isNotBlank(jsonStr) && StringUtils.startsWith(jsonStr, "[")
                && StringUtils.endsWith(jsonStr, "]");
    }


}
