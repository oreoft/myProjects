package cn.someget.mybatis.sqllog;

import cn.someget.mybatis.sqllog.bo.TUser;
import cn.someget.mybatis.sqllog.mapper.TUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Test测试模拟类
 * 方便观看就没在test包里面写了
 *
 * @author zyf
 * @date 2021-12-11 12:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisSqlLogTest {

    /**
     * 偷懒, 用mapper来演示查询日志展示效果
     */
    @Resource
    private TUserMapper tUserMapper;

    @Test
    public void selectPrintLogTest() {
        LambdaQueryWrapper<TUser> query = new LambdaQueryWrapper<TUser>()
                .select(TUser::getId, TUser::getPhone, TUser::getGender)
                .eq(TUser::getGender, 0)
                .likeRight(TUser::getUsername, "小");
        TUser result = tUserMapper.selectOne(query);
        System.out.printf("查询到的id是%s, 电话是%s\n", result.getId(), result.getPhone());
    }
}
