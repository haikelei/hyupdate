package com.empathy.domain.album.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class AlbumUpdBo {

    @ApiModelProperty("专辑名")
    private String albumName;

    @ApiModelProperty("专辑id")
    @Required
    private Long id;


    @ApiModelProperty("专辑简介 选填")
    private String detail;
    @ApiModelProperty("分类id 选填")
    private Long classifyId;

    @ApiModelProperty("收费方式 选填")
    private Integer type; //100全部免费 //200分级收费 //300全部收费

    @ApiModelProperty("前几集 选填")
    private Integer bumSet;

    @ApiModelProperty("是否接受会员分享 0接受 1不接受 选填")
    private Integer chargeStatus;

    @ApiModelProperty("每集多少钱 选填")
    private BigDecimal bumMoney;


}
