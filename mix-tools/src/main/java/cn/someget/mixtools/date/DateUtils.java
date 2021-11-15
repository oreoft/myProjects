package cn.someget.mixtools.date;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import static cn.someget.mixtools.date.DateConstant.ONE_HOUR_SECOND_COUNT;

/**
 * 有关日期相关的工具类
 * @author oreoft
 * @date 2021-05-26 10:05
 */
public class DateUtils {

    /**
     *  返回当前距离传日整点数的秒数(0-23)
     *<p>想要设置每日凌晨三点过期, 传入3
     *   如果今天没到凌晨三点就返回距离今天凌晨三点的值, 如果凌晨三点已过则返回明天的三点的
     * @param time 整点数
     * @return 当前距离给定time的时间差(单位秒)
     */
    public static long getToNextDaySec(Integer time) {
        LocalDateTime now = LocalDateTime.now();
        time = Optional.ofNullable(time).orElse(0);
        // 如果当前时间小于某点, 则返回当前时间到今天某点的秒差值
        if (now.getHour() < time) {
            DateUtil.endOfDay(new Date()).
                    between(Date.from(now.withHour(time).withMinute(0).withSecond(0).withNano(0).atZone(ZoneOffset.systemDefault()).toInstant()), DateUnit.SECOND);
        }
        // 如果不小于, 则表示时间已经过了, 返回距离次日某点的秒差值
        return (DateUtil.endOfDay(new Date()).between(new Date(), DateUnit.SECOND) + (time * ONE_HOUR_SECOND_COUNT));
    }
}
