package com.empathy.domain.log.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author tybest
 * @date 2017/10/25 11:56
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class LogVo {

    private Long id;
    private Long userId;
    private String userName;
    private int type;
    private String typeStr;
    private Long targetId;
    private Date created;
    private int times;
    private String province;
    private String city;
    private String country;
    private String content;


}
