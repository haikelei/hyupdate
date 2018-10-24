package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.domain.album.AlbumMoney;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IAlbumMoneyService;
import com.empathy.service.IBaseMemberService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by MI on 2018/4/16.
 */
@Service
public class AlbumMoneyService extends AbstractBaseService implements IAlbumMoneyService {

    @Override
    public RspResult save(AlbumMoney entity) {
        return null;
    }

    @Override
    public AlbumMoney findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(AlbumMoney entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
