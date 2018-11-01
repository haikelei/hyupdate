package com.empathy.domain.baseRecording.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/4/23.
 */
@Getter
@Setter
public class RecordPlayVo {


    // 录音id
    private Long id;

    // url
    private String playUrl;

    // 专辑封页
    private String albumUrl;

    // 用户id
    private Long userId;



}
