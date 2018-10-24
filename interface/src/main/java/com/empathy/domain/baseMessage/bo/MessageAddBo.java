package com.empathy.domain.baseMessage.bo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/6/5.
 */
@Getter@Setter
public class MessageAddBo {

    private Integer code;
    private String title;

    private String content;

    private String headMessage;
}
