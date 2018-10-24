package com.empathy.domain.account.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/11.
 */
@Getter
@Setter
public class ProveListBo extends PageBo {


    private Integer sex;

    private Integer liveStatus;

    private Long classifyId;

    private String str;
}
