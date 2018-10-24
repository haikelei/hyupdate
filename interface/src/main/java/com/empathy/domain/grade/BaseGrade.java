package com.empathy.domain.grade;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * @ Description    等级配置
 * @ Author         dong
 * @ Date           2018-09-20 14:26
 */
@Getter
@Setter
public class BaseGrade extends BaseDomain {

    /** 等级 */
    private String grade;

    /** 最小积分*/
    private Integer minExp;

    /** 最大积分 */
    private Integer maxExp;

    /** 直播时间（可获取经验值的时间） */
    private Integer liveTime;

    /** 在直播时间获得的经验值 */
    private Integer exp;

    /** 积分规则 */
    private String rule;


}
