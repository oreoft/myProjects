package cn.someget.mybatis.sqllog.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 模拟用户的BO
 *
 * @author zyf
 * @date 2021-12-11 12:12
 */
@Data
@TableName("t_user")
public class TUser {

    /**
     * pk
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模拟用户名字
     */
    private String username;

    /**
     * 模拟用户密码, 方便演示 明文存储
     */
    private String password;

    /**
     * 模拟用户性别
     */
    private Integer gender;

    /**
     * 模拟用户手机
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
