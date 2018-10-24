package com.empathy.domain.baseGift;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/25.
 */
@Getter
@Setter
public class BaseGift extends BaseDomain {

    private String giftName;

    private BigDecimal price;
    private BigDecimal defaultPrice;
    private BigDecimal money;
    private BigDecimal defaultMoney;
    private Integer code;
    private String url;

    /**
     * 该礼物的经验值
     */
    private Integer exp;


}
