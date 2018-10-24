package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/27.
 */
@Getter@Setter
public class Live {

    private String roomId; //房间号id

    private Boolean valid; //有效的

    private String announcement;//公告

    private String name;//直播间名字

    private String broadcasturl;//直播间地址

    private String creator;//创建人


}
