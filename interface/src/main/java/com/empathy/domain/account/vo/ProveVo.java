package com.empathy.domain.account.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/11.
 */
@Getter
@Setter
public class ProveVo {

    private Long id;

    private Long liveId;

    private String phone;

    private Integer sex;
    private String username;
    private String liveCode;

    private Integer liveStatus;

    private Integer level;

    private Integer newCode;

    private String theme;

    private String title;

    private Double timeSum;

    private Double gift;

    private Integer memberStatus;

    private Long createTime;

    private Integer code;
    private Integer delFlag;
    private Long classifyId;
    private Integer personCount;
    private String classifyName;


}
