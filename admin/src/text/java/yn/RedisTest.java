package com.chitry.yn;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chitry.yn.common.utils.RedisUtils;
import com.chitry.yn.modules.sys.entity.SysUserEntity;

/**
 * <p>Title: RedisTest</p>
 * <p>Description: Redis测试</p>
 * <p>Company: 浙江企业在线有限公司</p> 
 * @author chitry@126.com
 * @date 2018年4月24日 下午7:49:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        SysUserEntity user = new SysUserEntity();
        user.setEmail("123456@qq.com");
        redisUtils.set("user", user);

        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("user", SysUserEntity.class)));
    }

}
