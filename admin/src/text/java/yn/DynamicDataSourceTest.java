package com.chitry.yn;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chitry.yn.modules.sys.entity.SysUserEntity;
import com.chitry.yn.service.DataSourceTestService;

/**
 * <p>Title: DynamicDataSourceTest</p>
 * <p>Description: 多数据源测试</p>
 * <p>Company: 浙江企业在线有限公司</p> 
 * @author chitry@126.com
 * @date 2018年4月24日 下午7:49:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    public void test(){
        //数据源1
        SysUserEntity user1 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user1));

        //数据源2
        SysUserEntity user2 = dataSourceTestService.queryUser2(1L);
        System.out.println(ToStringBuilder.reflectionToString(user2));

        //数据源1
        SysUserEntity user3 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user3));
    }

}
