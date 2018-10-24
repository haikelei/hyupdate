package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/25.
 */
@Getter
@Setter
public class BaseLiveGift extends BaseDomain {

    private Long liveId;

    private Long userId;

    private Long liveUserId;

    private Long giftId;

    private BigDecimal price;

    private BigDecimal money;

    private Integer count;
}
