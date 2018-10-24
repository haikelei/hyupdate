package com.empathy.domain.configuration;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/17.
 */
@Getter
@Setter
public class Configuration extends BaseDomain {

    private Double conversion;

    private String type;


}
