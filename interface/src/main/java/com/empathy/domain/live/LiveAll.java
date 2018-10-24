package com.empathy.domain.live;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/28.
 */
@Getter@Setter
public class LiveAll {

    private Long id;

    private Integer code;

    private Long userId;

    private String theme;

    private Integer level;

    private Integer liveStatus;

    private Integer liveNumber;

    private String title;

    private String liveCode;

    private Double timeSum;

    private Integer personCount;

    private Long classifyId;

    private String roomId;
    private String broadcasturl;
    private String cid;
    private String name;
    private String pushUrl;
    private String rtmpPullUrl;
    private String httpPullUrl;
    private String hlsPullUrl;

    private Integer focusStatus;
    private Integer RoomManageStatus;//是否是房管// 0 不是1 是
    private Integer RoomBlackStatus;//是否被拉黑// 0不是 1是

    private Double giftMoney;//礼物金额
    private Integer rank;//排名


}
