package com.empathy.domain.live;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/5.
 */
@Getter@Setter
public class RoomManage  extends BaseDomain{

    private Long userId;

    private Long liveId;

    private Long manageUserId;

    private Integer status;

    private Integer freezeStatus;

    private String username;

    private String url;

}
