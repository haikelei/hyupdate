package com.empathy.service.impl;

import com.empathy.common.IdWorker;
import com.empathy.common.RspResult;
import com.empathy.dao.PropertyDao;
import com.empathy.domain.KeyValueVo;
import com.empathy.domain.property.Property;
import com.empathy.domain.property.bo.PropertyBo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tybest
 * @date 2017/8/7 14:24
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class PropertyService extends AbstractBaseService implements IPropertyService {
    //缓存
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    private final String SINGLE_PRIFIX = "single_";
    private final String MULTI_PRIFIX = "multi_";

    @Autowired
    private PropertyDao propertyDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult save(Property entity) {
        entity.setId(IdWorker.genId());
        propertyDao.save(entity);
        return success(entity);
    }

    @Override
    public Property findById(Long aLong) {
        return propertyDao.findById(aLong);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult update(Property entity) {
        propertyDao.update(entity);
        return success(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public RspResult delById(Long aLong) {
        final Property p = propertyDao.findById(aLong);
        if (p != null) {
            propertyDao.delById(aLong);
        }
        return success();
    }

    @Override
    public String getTextById(Long id) {
        if (id == null) {
            return "";
        }
        final String key = SINGLE_PRIFIX + id;
        Object content = null;
        if (cache.containsKey(key)) {
            content = cache.get(key);
        } else {
            Property p = propertyDao.findById(id);
            if (p != null) {
                content = p.getContent();
                cache.putIfAbsent(key, content);
            }
        }
        return String.valueOf(content);
    }

    @Override
    public List<KeyValueVo> findByClazz(String clazz) {
        List<KeyValueVo> list = null;
        final String key = MULTI_PRIFIX + clazz;
        if (cache.containsKey(key)) {
            list = (List<KeyValueVo>) cache.get(key);
        } else {
            list = propertyDao.findByClazz(clazz);
            cache.putIfAbsent(key, list);
        }
        return list;
    }

    @Override
    public RspResult page(PropertyBo bo) {
        final long count = propertyDao.count(bo);
        List<Property> vos = new ArrayList<>();
        if (count > 0) {
            vos = propertyDao.list(bo);
        }
        return success(count, vos);
    }

    @Override
    public String findOneByClazz(String clazz) {
        final String key = SINGLE_PRIFIX + clazz;
        if (cache.containsKey(key)) {
            return String.valueOf(cache.get(key));
        } else {
            List<KeyValueVo> list = propertyDao.findByClazz(clazz);
            if (list != null && list.size() > 0) {
                cache.putIfAbsent(key, list.get(0).getText());
                return String.valueOf(cache.get(key));
            }
        }
        return null;
    }

    @Override
    public void refresh() {
        cache.clear();
    }
}
