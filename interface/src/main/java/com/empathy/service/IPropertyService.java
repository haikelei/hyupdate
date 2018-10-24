package com.empathy.service;

import com.empathy.common.BaseService;
import com.empathy.common.RspResult;
import com.empathy.domain.KeyValueVo;
import com.empathy.domain.property.Property;
import com.empathy.domain.property.bo.PropertyBo;

import java.util.List;

/**
 * @author tybest
 * @date 2017/8/7 14:14
 * @email zhoujian699@126.com
 * @desc
 **/
public interface IPropertyService extends BaseService<Property, Long, PropertyBo> {


    /**
     * 根据id获取名称
     *
     * @param id
     * @return
     */
    String getTextById(Long id);

    List<KeyValueVo> findByClazz(String clazz);

    RspResult page(PropertyBo bo);

    /**
     * 通过类别获得一个
     *
     * @param clazz
     * @return
     */
    String findOneByClazz(String clazz);

    void refresh();
}
