package com.empathy.domain.bidding.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MI on 2018/1/28.
 */
@Getter
@Setter
public class DealFindBo extends PageBo {
    @ApiModelProperty("用户id")
    @Required
    private Long userId;

    @ApiModelProperty("交易类型 0全部 1提现 2返利 3付款")
    @Required
    private Integer type;


}
