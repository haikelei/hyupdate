package com.empathy.domain.live.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/29.
 */
@Getter
@Setter
public class ProveAccountUpdBo {

    private Long id;

    private Integer code;

    private Integer personCount;

    private Integer newCode;
}
