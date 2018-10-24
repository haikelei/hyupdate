package com.empathy.domain.album;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/16.
 */
@Getter
@Setter
public class Album extends BaseDomain {

    private String albumName;

    private Long ClassifyId;

    private Long userId;

    private String detail;


}
