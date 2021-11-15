package cn.someget.mixtools.collection;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * map相关的工具类
 * @author oreoft
 * @date 2021-05-26 10:05
 */
public class MapUtils {

    /**
     * 这个可以方法可以根据里面具体的key来去重
     * 因为Stream的distinct去重不能根据key来去重, 只能比较元素整体是不是相同
     * @param mapping 转换
     * @param <T> 传入一个func, 获取要去重的key
     * @return 返回一个断言,表示这个是不是重复
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> mapping) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(mapping.apply(object), Boolean.TRUE) == null;
    }
}
