package com.empathy.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/9/10 11:30
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Select2Bo {
    private String q;
    private Integer page = 1;

    private String clazz;
}
