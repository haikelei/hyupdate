package com.empathy.domain.album.vo;

import com.empathy.domain.album.Album;
import com.empathy.domain.album.AlbumMoney;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class AlbumVo {

    private Long id;

    private String url;

    private String albumName;

    private String ClassifyName;

    private Long userId;

    private String detail;

    private Long createTime;

    private Integer type;

    private Integer chargeStatus;

    private Integer bumSet;

    private BigDecimal bumMoney;

    private Integer count;

    private  String username;

    /**
     * 专辑播放量
     */
    private Integer payNumber;

}
