package com.empathy.web;

import com.empathy.common.RspResult;
import com.empathy.domain.comments.bo.CommentOpenBo;
import com.empathy.domain.comments.bo.CommentOpenFindBo;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.service.ITbCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MI on 2018/6/6.
 */
@RestController
@RequestMapping("/comment")
@Api(tags = {"评论接口"})
public class TbCommentController {

    @Autowired
    private ITbCommentService tbCommentService;

    @ApiOperation(value = "关闭或开启评论", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/openComment", method = RequestMethod.POST)
    public RspResult openComment(CommentOpenBo bo) {


        return tbCommentService.openComment(bo);
    }

    @ApiOperation(value = "根据类型查看评论开启状态", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findComment", method = RequestMethod.POST)
    public RspResult findComment(CommentOpenFindBo bo) {


        return tbCommentService.findComment(bo);
    }

    @ApiOperation(value = "后台查看全部", httpMethod = "POST", response = String.class)
    @RequestMapping(value = "/findAllComment", method = RequestMethod.POST)
    public RspResult findAllComment() {


        return tbCommentService.findAllComment();
    }




}
