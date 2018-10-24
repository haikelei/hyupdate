package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/24.
 */
@Getter
@Setter
public class BaseLiveTime extends BaseDomain {

    private Long liveId;

    private Long startTime;

    private Long endTime;


}
