package com.empathy.domain.album.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/5/21.
 */
@Getter
@Setter
public class AlbumAccountVo {

    private String phone;

    private String username;

    private Long id;

    private String albumName;

    private String ClassifyName;

    private Long userId;

    private String detail;

    private Long createTime;

    private Integer type;

    private Integer chargeStatus;

    private Integer bumSet;

    private BigDecimal bumMoney;

}
