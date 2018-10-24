package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/8/12.
 */
@Getter@Setter
public class LiveBlacklist extends BaseDomain {

    private Long userId;
    private Long liveId;

}
