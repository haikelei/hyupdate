package com.empathy.domain.user;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/13.
 */
@Getter
@Setter
public class BaseMember extends BaseDomain {

    private String username;

    private Integer sex;

    private Integer level;

    private String phone;

    private String token;

    private String intro;//个性签名 数据库没加数据是不会在json数据中显示出来的

    private String address;//地区 数据库没加数据是不会在json数据中显示出来的

    private Integer login_status = 0;

    private Integer proveStatus; //0代表不是主播 1为主播 2为审核中就不能提交了 除了0可以提交主播

    private Integer memberStatus;//0不是会员1是

    private String url;

    private Long lastDate;

    private Integer passwordStatus; //有没有密码

    private Integer proveLevel;//主播等级

    private Integer experience; //经验值

    private Long endTime;//会员截至时间为0表示不是会员，不为0市你们用这个时间减当前时间就行了

    private String pushUrl;//推流地址

    private String roomId;//网易房间号id

    private String rtmpPullUrl;//拉流地址

    private Integer liveStatus;//直播间状态0未开启1开启
    private Long liveId;

    private Integer RoomManageStatus;//是否是房管// 0 不是1 是
    private Integer RoomBlackStatus;//是否被拉黑// 0不是 1是

    private Double giftMoney;//礼物金额
    private Integer rank;//排名

    private String accid;

    private Integer focusStatus;

    private String fileAccid;
    private String fileToken;
}
