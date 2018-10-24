package com.empathy.domain.bidding;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2017/12/21.
 */
@Getter
@Setter
public class File extends BaseDomain {

    private String location;
    private String type;
    private String purpose;
    private Long purposeId;


}
