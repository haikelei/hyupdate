package com.empathy.domain.baseMessage.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/5.
 */
@Getter@Setter
public class MessageUpdBo {

    private Long id;

    private String content;

    private String headMessage;

    private Integer code;

    private String title;

}
