package com.empathy.domain.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class FindPhone {

    private String phone;

    private String type;

    public FindPhone(String phone, String type) {
        this.type = type;
        this.phone = phone;
    }
}
