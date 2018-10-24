package com.empathy.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/10 11:14
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class TreeNode {

    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("内容")
    private String text;
    @ApiModelProperty("扩展信息")
    private String ext;
    @ApiModelProperty("父ID")
    private String pid;
    @ApiModelProperty("子节点")
    private List<TreeNode> children = new ArrayList<>();
}
