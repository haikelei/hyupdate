package com.empathy.dao;

import com.empathy.common.BaseDao;
import com.empathy.domain.KeyValueVo;
import com.empathy.domain.property.Property;
import com.empathy.domain.property.bo.PropertyBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:16
 * @email zhoujian699@126.com
 * @desc
 **/
public interface PropertyDao extends BaseDao<Property, Long, PropertyBo> {

    List<KeyValueVo> findByClazz(String clazz);

    long count(PropertyBo bo);

    List<Property> list(PropertyBo bo);
}
