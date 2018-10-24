package com.empathy.domain.banner;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/5.
 */
@Getter@Setter
public class BaseBanner extends BaseDomain
{
    private Integer type;
    private String url;
    private String image;
    private String title;
    private int code;

}
