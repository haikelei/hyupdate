package com.empathy.domain.banner.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/5.
 */
@Getter@Setter
public class BannerFindBo {

    @ApiModelProperty(value = "banner分类0推荐banner 1直播banner 2精品banner")
    @Required
    private Integer type;

}
