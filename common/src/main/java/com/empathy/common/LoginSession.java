package com.empathy.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/5 11:09
 * @email zhoujian699@126.com
 * @desc 登录缓存
 **/
@Getter
@Setter
public class LoginSession {

    private Long id;
    private String username;
    private List<String> permissiones = new ArrayList<>();
    private List<String> roles = new ArrayList<>();
}
