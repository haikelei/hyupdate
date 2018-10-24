package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/27.
 */
@Getter@Setter
public class BaseChannel {

    private String cid;//频道ID，32位字符串
    private String name;//频道名称
    private  String pushUrl;//推流地址
    private String httpPullUrl;//http拉流地址
    private String hlsPullUrl;//hls拉流地址
    private String rtmpPullUrl;//rtmp拉流地址

}
