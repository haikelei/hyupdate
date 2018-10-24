package com.empathy.common;

/**
 * @author tybest
 * @date 2017/8/5 8:45
 * @email zhoujian699@126.com
 * @desc
 **/
public interface BaseService<T extends BaseDomain, ID, Q extends PageBo> {
    /**
     * 新增
     *
     * @param entity
     */
    RspResult save(T entity);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 修改
     *
     * @param entity
     */
    RspResult update(T entity);

    /**
     * 删除
     *
     * @param id
     */
    RspResult delById(ID id);
}
