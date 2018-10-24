package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.AlbumDao;
import com.empathy.dao.BaseLiveDao;
import com.empathy.dao.UserCollectionDao;
import com.empathy.dao.UserFocusDao;
import com.empathy.domain.album.vo.AlbumVo;
import com.empathy.domain.collection.UserCollection;
import com.empathy.domain.collection.bo.CollectionAddBo;
import com.empathy.domain.collection.bo.CollectionFindBo;
import com.empathy.domain.live.BaseLive;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.domain.user.vo.UserFocusVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IUserCollectionService;
import com.empathy.service.IUserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/18.
 */
@Service
public class UserCollectionService extends AbstractBaseService implements IUserCollectionService {

    @Autowired
    private UserCollectionDao collectionDao;

    @Autowired
    private BaseLiveDao liveDao;

    @Autowired
    private AlbumDao albumDao;

    @Override
    public RspResult findCollection(CollectionFindBo bo) {
        if(bo.getType()==100){
            //查看直播间
           List<BaseLive> lives =  liveDao.findForCollection(bo);
            int count  = liveDao.findForCollectionCount(bo);
            return success(count,lives);
        }else if(bo.getType()==200){
            //音频列表
            List<AlbumVo> albumVos = albumDao.findAlbumForCollection(bo);
            int count = albumDao.findAlbumForCollectionCount(bo);
            return success(count,albumVos);

        }

        return errorNo();
    }

    @Override
    public RspResult cancelCollection(Long id) {
        UserCollection byId = collectionDao.findById(id);
        if(byId==null){
            return errorMo();
        }
        collectionDao.delById(id);

        return success();
    }

    @Override
    public RspResult addCollection(CollectionAddBo bo) {
        UserCollection collection = new UserCollection();
        collection.setType(bo.getType());
        collection.setTypeId(bo.getTypeId());
        collection.setUserId(bo.getUserId());
        collectionDao.save(collection);

        return success(collection);
    }

    @Override
    public RspResult save(UserCollection entity) {
        return null;
    }

    @Override
    public UserCollection findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(UserCollection entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
