package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/28.
 */
@Getter@Setter
public class BaseLiveChannel extends BaseDomain{

    private Long userId;
    private Long liveId;
    private String roomId;
    private String broadcasturl;
    private String cid;
    private String name;
    private String pushUrl;
    private String rtmpPullUrl;
    private String httpPullUrl;
    private String hlsPullUrl;
    private String fileToken;
    private String fileAccid;



}
