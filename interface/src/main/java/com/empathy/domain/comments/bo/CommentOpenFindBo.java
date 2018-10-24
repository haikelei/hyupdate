package com.empathy.domain.comments.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/6/6.
 */
@Getter@Setter
public class CommentOpenFindBo{

    @ApiModelProperty("评论的类型 0动态 1专辑")
    @Required
    private Integer type;
}
