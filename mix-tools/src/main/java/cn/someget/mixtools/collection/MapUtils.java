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
 * @author zyf
 * @date 2021-05-26 10:05
 * @describe :
 */
public class MapUtils {


    @Test
    public void distinctByKeyTest() {
        List<Map<Object, Object>> list = Lists.newArrayList(
                MapUtil.builder().put("name", "xiaoming").build(),
                MapUtil.builder().put("name", "xiaohong").build(),
                MapUtil.builder().put("name", "xiaoming").build());
        System.out.println(list.stream().filter(distinctByKey(e -> e.get("name"))).collect(Collectors.toList()));

    }


    /**
     * 因为stram的distinct去重不能根据key来去重, 只能比较元素整体是不是相同, 这个可以方法可以根据里面具体的key来去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }
}
