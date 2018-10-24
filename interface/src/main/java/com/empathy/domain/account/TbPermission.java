package com.empathy.domain.account;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/25.
 */
@Getter@Setter
public class TbPermission extends BaseDomain{

    private String memo;
    private Integer code;
    private String tag;
    private Integer flag;

}
