package com.empathy.domain.baseRecording;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class BaseRecording extends BaseDomain {

    private Long albumId;

    private String title;

    private Long userId;

    private Integer playNumber;

    private Integer clickNumber;

    private String username;

    private BigDecimal money;

    private String url;

    private Integer buyStatus=0;

    private String sign;

}
