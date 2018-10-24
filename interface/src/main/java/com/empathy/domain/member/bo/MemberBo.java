package com.empathy.domain.member.bo;

import com.empathy.common.PageBo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/7 14:11
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@ApiModel("运营商或者平台用户查询参数")
public class MemberBo extends PageBo {

    private String username;
}
