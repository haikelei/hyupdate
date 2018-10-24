package com.empathy.domain.property.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/7 14:13
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class PropertyBo extends PageBo {

    private String clazz;
    private String name;

    private String sort;
    private String order;
}
