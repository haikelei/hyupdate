package com.empathy.domain.album.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by MI on 2018/8/24.
 */
@Getter@Setter
public class AlbumBestVo {

    private List<AlbumVo> albumVo;
    private String title;
    private Long titleId;

}
