package com.empathy.domain.property;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/7 14:12
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Property extends BaseDomain {

    private String clazz;
    private String name;
    private String content;
    private Integer indexing;
    private String memo;
    private String ext1;//扩展
    private String ext2;//扩展
}
