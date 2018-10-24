package com.empathy.domain.album.bo;

import com.empathy.common.PageBo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/5/21.
 */
@Getter
@Setter
public class FindAlbumForAccountBo extends PageBo {


    private String str;

    private Integer moneyStatus;

    private Integer memberStatus;

    private Long classifyId;
}
