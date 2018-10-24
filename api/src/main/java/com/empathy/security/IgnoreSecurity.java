/**
 *
 */
package com.empathy.security;

import java.lang.annotation.*;

/**
 * @author zhoujian
 * @date 2015年10月26日 下午2:02:11
 * @desc 忽略安全检查
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {

}
