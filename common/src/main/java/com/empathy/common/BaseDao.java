package com.empathy.common;

/**
 * @author tybest
 * @date 2017/8/5 8:44
 * @email zhoujian699@126.com
 * @desc
 **/
public interface BaseDao<T extends BaseDomain, ID, Q extends PageBo> {
    /**
     * 新增
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 查询-返回原始实体类
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
    void update(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delById(ID id);
}
