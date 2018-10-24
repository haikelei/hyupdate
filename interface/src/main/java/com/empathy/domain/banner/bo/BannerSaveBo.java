package com.empathy.domain.banner.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/5.
 */
@Getter@Setter
public class BannerSaveBo {


    @ApiModelProperty(value = "banner分类0推荐banner 1直播banner 2精品banner")
    @Required
    private Integer type;

    @ApiModelProperty(value = "bannerurl")
    @Required
    private String url;

    @ApiModelProperty(value = "banner image")
    @Required
    private String image;

    @ApiModelProperty(value = "banner 标题")
    @Required
    private String title;
    @ApiModelProperty(value = "banner code")
    @Required
    private Integer code;

}
