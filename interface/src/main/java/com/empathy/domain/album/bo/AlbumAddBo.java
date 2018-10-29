package com.empathy.domain.album.bo;

import com.empathy.common.BaseDomain;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class AlbumAddBo {

    @ApiModelProperty("专辑名")
    @Required
    private String title;
    @ApiModelProperty("分类id")
    @Required
    private Long classifyId;
    @ApiModelProperty("用户id")
    @Required
    private Long userId;
    @ApiModelProperty("专辑详情")
    @Required
    private String detail;

    @ApiModelProperty("收费方式//100全部免费 //200分级收费 //300全部收费")
    @Required
    private Integer feeType; //100全部免费 //200分级收费 //300全部收费

    @ApiModelProperty("前几集")
    private Integer bumSet;

    @ApiModelProperty("是否接受会员分享 0接受 1不接受")
    @Required
    private Integer shareType;

    @ApiModelProperty("每集多少钱")
    @Required
    private BigDecimal bumMoney;

    @ApiModelProperty("封面图片")
    @Required
    private String url;


}
