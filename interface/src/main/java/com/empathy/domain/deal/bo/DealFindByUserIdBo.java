package com.empathy.domain.deal.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/18.
 */
@Getter
@Setter
public class DealFindByUserIdBo extends PageBo {


    @ApiModelProperty("用户id")
    @Required
    private Long id;


}
