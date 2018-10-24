package com.empathy.domain.bidding;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2017/12/21.
 */
@Getter
@Setter
public class Complain extends BaseDomain {

    private String title;
    private String name;
    private String depict;
    private String phone;
    private Long userId;
}
