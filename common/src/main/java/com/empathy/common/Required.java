/**
 *
 */
package com.empathy.common;

import java.lang.annotation.*;

/**
 * @author tyb
 * @date 2016年7月10日下午3:59:29
 * @desc 必填项，不能为空 或 null，只针对字符串，基本类型
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    /**
     * 提示名
     *
     * @return
     */
    String name() default "";
}
