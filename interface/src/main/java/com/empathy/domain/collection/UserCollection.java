package com.empathy.domain.collection;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/24.
 */
@Getter@Setter
public class UserCollection extends BaseDomain {

    private Integer type;//100直播间200音频
    private Long typeId;
    private Long userId;
}
