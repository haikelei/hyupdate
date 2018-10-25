package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.log.bo.LogBo;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.domain.user.vo.UserFocusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MI on 2018/4/18.
 */
public interface UserFocusDao extends BaseDao<UserFocus, Long, LogBo> {
    Integer count(FocusFindBo bo);

    List<UserFocusVo> list(FocusFindBo bo);

    int findCountByIds(@Param(value = "id") Long id,@Param(value = "userId") Long userId);

    UserFocus findByIds(@Param(value = "id") Long id,@Param(value = "userId") Long userId);

    UserFocus findByUserIdAndFocusId(@Param(value = "userId") Long userId,@Param(value = "userFocusId") Long userFocusId);

    /** 重新关注 */
    int againUpdataById(Long id);



}
