package com.empathy.domain.comments.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class CommentsFindBo extends PageBo {

    @ApiModelProperty("评论对象的id")
    @Required
    private Long commentId;

    @ApiModelProperty("评论的类型对什么对象评论的")
    @Required
    private String commentType;
}
