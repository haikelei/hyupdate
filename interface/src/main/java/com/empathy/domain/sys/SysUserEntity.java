package com.empathy.domain.sys;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by MI on 2018/4/27.
 */
@Getter
@Setter
public class SysUserEntity {


    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 创建时间
     */
    private Date createTime;

//	/**
//	 * 部门ID
//	 */
//	@NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
//	private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

}
