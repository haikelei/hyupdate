package com.empathy.domain.baseMessage.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/5.
 */
@Getter@Setter
public class MessageFindBo extends PageBo {

    @ApiModelProperty("用户id")
    @Required
    private Long id;


}
