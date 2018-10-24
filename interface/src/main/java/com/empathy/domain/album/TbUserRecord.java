package com.empathy.domain.album;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/30.
 */
@Getter@Setter
public class TbUserRecord extends BaseDomain{
    private Long albumId;

    private Long recordId;

    private Long userId;
}
