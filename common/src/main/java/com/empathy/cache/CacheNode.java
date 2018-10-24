package com.empathy.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/7/5 17:11
 * @desc
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheNode implements Serializable {

    private Object data;
    private long expire = 0;

}
