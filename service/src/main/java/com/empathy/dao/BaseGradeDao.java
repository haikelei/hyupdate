package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.common.PageBo;
import com.empathy.domain.grade.BaseGrade;

/**
 * @ Description    等级经验
 * @ Author         dong
 * @ Date           2018-09-20 14:05
 */
public interface BaseGradeDao extends BaseDao<BaseGrade,Long,PageBo> {

    /** 根据经验查询等级 */
    BaseGrade findByExp(Integer exp);

}
