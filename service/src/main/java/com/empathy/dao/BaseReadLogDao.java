package com.empathy.dao;

import com.empathy.domain.baseReadLog.BaseReadLog;
import org.apache.ibatis.annotations.Param;

/**
 * @ Description
 * @ Author         dong
 * @ Date           2018-09-25 19:30
 */
public interface BaseReadLogDao {

    int insert(BaseReadLog baseReadLog);

    BaseReadLog findByUserAndArticle(@Param("articleId") Long articleId, @Param("userId") Long userId);


}
