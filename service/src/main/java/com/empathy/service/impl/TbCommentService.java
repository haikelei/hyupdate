package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.TbCommentDao;
import com.empathy.domain.comments.TbComment;
import com.empathy.domain.comments.bo.CommentOpenBo;
import com.empathy.domain.comments.bo.CommentOpenFindBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.ITbCommentService;
import com.empathy.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/6/6.
 */
@Service
public class TbCommentService  extends AbstractBaseService implements ITbCommentService {

    @Autowired
    private TbCommentDao tbCommentDao;


    @Override
    public RspResult findAllComment() {

        try {

            List<TbComment> list = tbCommentDao.list();
            return success(list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult findComment(CommentOpenFindBo bo) {
        TbComment tbComment = tbCommentDao.findByType(bo.getType());

        return success(tbComment);
    }

    @Override
    public RspResult openComment(CommentOpenBo bo) {
        TbComment tbComment = tbCommentDao.findById(bo.getId());
        if(bo.getType()==0){
            //开启
            tbComment.setStatus(0);

        }else {
            //关闭
            tbComment.setStatus(1);

        }
        try {

            tbCommentDao.update(tbComment);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult save(TbComment entity) {
        return null;
    }

    @Override
    public TbComment findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(TbComment entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
