package com.empathy.cache;

/**
 * @author tybest
 * @date 2017/11/6 18:07
 * @email zhoujian699@126.com
 * @desc
 **/
public interface ICache {

    void add(String key, Object value, long expire);

    void add(String key, Object value);

    void remove(String key);

    Object get(String key);
}
