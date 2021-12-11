package cn.someget.mybatis.sqllog.mapper;

import cn.someget.mybatis.sqllog.bo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表的mapper
 *
 * @author zyf
 * @date 2021-12-11 12:12
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
}
