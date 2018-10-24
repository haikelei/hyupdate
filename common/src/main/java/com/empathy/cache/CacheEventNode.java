package com.empathy.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/9/1 10:26
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@AllArgsConstructor
public class CacheEventNode {
    private String flag;
    private String key;
    private CacheNode node;
}
