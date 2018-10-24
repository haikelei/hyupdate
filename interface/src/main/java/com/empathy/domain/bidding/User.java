package com.empathy.domain.bidding;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2017/12/22.
 */
@Getter
@Setter
public class User extends BaseDomain {

    private String phone;

    private String username;

    private String password;

    private Integer sex;

    private Integer type;

    private String code;

    private Double latitude;

    private Double longitude;

    private Integer distance;

    private String tbOrderType;

    private String city;
}
