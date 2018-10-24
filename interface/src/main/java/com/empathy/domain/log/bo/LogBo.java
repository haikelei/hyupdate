package com.empathy.domain.log.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/10/16 10:13
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class LogBo extends PageBo {

    /**
     * @see com.empathy.common.Constants.LogType
     */
    private int type;
    private String province;
    private String city;
    private String country;
    private String startDate;
    private String endDate;
    /**
     * 1日2周3月
     * 默认 7天的数据
     * 默认 4周
     * 默认 4月
     */
    private int flag = 1;
}
