package com.empathy.domain.album;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/18.
 */
@Getter@Setter
public class BaseNice extends BaseDomain {


    private Long parentId;
    private Integer code;

    private Long albumId;


}
