package com.empathy.domain.live.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/16.
 */
@Getter@Setter
public class GiveGiftVo {

    private String url;
    private String name;
    private Integer sex;
    private Integer liveStatus;
    private double moneySum;
    private Long userId;
}
