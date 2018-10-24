package com.empathy.service;

import com.empathy.domain.sys.SysUserEntity;
import com.empathy.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by MI on 2018/4/27.
 */
public interface ISysUserService {
    //PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

 /*   *//**
     * 保存用户
     *//*
    void save(SysUserEntity user);

    *//**
     * 修改用户
     *//*
    void update(SysUserEntity user);

    *//**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     *//*
    boolean updatePassword(Long userId, String password, String newPassword);*/

}
