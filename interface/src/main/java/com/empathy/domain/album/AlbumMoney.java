package com.empathy.domain.album;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class AlbumMoney extends BaseDomain {

    private Long albumId;

    private Integer type;

    private Integer chargeStatus;

    private Integer bumSet;

    private BigDecimal bumMoney;

    private String albumName;

}
