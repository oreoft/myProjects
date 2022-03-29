package cn.someget.mixtools;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.csv.CsvRow;
import cn.someget.mixtools.collection.ListUtils;
import cn.someget.mixtools.collection.MapUtils;
import cn.someget.mixtools.file.ReaderUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 测试类
 * @author oreoft
 * @date 2021-05-26 10:05
 */
public class MixToolsTest {

    @Test
    public void findMaxTest() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 1, 2, 4, 4);
        Map<Integer, Long> ageCollect = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).
                entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oleValue, newValue) -> oleValue, LinkedHashMap::new));
        System.out.println(ListUtils.findMax(ageCollect));
    }

    @Test
    public void distinctByKeyTest() {
        List<Map<Object, Object>> list = Lists.newArrayList(
                MapUtil.builder().put("name", "xiaoming").build(),
                MapUtil.builder().put("name", "xiaohong").build(),
                MapUtil.builder().put("name", "xiaoming").build());
        System.out.println(list.stream().filter(MapUtils.distinctByKey(e -> e.get("name"))).collect(Collectors.toList()));
    }


    @Test
    public void CSVTest() {
        // 需要写入的 csv 文件路径
        final String filePath = "/Users/oreoft/Downloads/_data_jmeter-log_cookieToken.30w.txt";
        List<CsvRow> csvRows = ReaderUtils.readCsvFile(filePath);
        List<Long> result = csvRows.stream().map(e -> Convert.toLong(e.get(0))).collect(Collectors.toList());
        System.out.println(result);
    }

}
