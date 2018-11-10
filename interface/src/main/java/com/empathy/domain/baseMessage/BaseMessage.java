package com.empathy.domain.baseMessage;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/5.
 */
@Getter@Setter
public class BaseMessage extends BaseDomain{

    private Integer code;

    private String  headMessage;

    private String title;

    private  String content;

    private Integer type;

    private Integer readStatus;

    private Long userId;

}
