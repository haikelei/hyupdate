package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class BaseLive extends BaseDomain {

    private Integer code;

    private Long userId;

    private String theme;

    private String url;

    private Integer level;

    private Integer liveStatus;

    private Integer liveNumber;

    private String title;

    private String liveCode;

    private Double timeSum;

    private Integer personCount;

    private Long classifyId;

    private Integer newCode;

    private Long beginTime;

    private String username;

}
