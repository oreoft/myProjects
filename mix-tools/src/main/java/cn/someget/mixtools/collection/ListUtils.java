package cn.someget.mixtools.collection;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * list相关的工具类
 * @author oreoft
 * @date 2021-05-26 10:05
 */
public class ListUtils {

    /**
     * 传入计算好频次的map集合, 返回value最大的key
     *
     * @param collect 传入的应该是一个有序的map(LinkedHashMap)
     * @return 返回值是一个list, 因为如果value的最大值有相同, 则对应的key都会返回
     */
    public static List<Integer> findMax(Map<Integer, Long> collect) {
        List<Map<Integer, Long>> maxList = CollUtil.newLinkedList();
        collect.forEach((k, v) -> {
            if (CollectionUtil.isEmpty(maxList)) {
                // 如果第一次进来它就是最大值
                maxList.add(MapUtil.builder(k, v).build());
            } else {
                // 不是第一次进来就和最后一个元素对比一下, 如果相等则加入, 如果不相等则直接返回
                if (v.equals(CollUtil.getLast(maxList).values().stream().findFirst().orElse(0L))) {
                    maxList.add(MapUtil.builder(k, v).build());
                }
            }
        });
        // 把maxList里面的值都返回
        return maxList.stream().flatMap(e -> e.keySet().stream()).collect(Collectors.toList());
    }
}
