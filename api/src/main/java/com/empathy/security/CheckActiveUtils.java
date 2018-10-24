package com.empathy.security;

import com.empathy.cache.CacheUtils;
import com.empathy.common.Constants;
import com.empathy.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tybest
 * @date 2017/10/23 14:13
 * @email zhoujian699@126.com
 * @desc 校验当天是否使用APP
 **/
public final class CheckActiveUtils {

    private static final Set<Long> cache = new HashSet<>();
    private static final long def_expire = 5 * 60 * 1000;
    private static volatile String curDate = null;

    /**
     * 判断用户当天是否登录过
     *
     * @param userId
     * @return
     */
    public static boolean checkActive(Long userId) {
        String now = DateUtils.getDateStr(new Date(), DateUtils.DEF_DATE);
        if (curDate == null) {
            curDate = now;
            cache.add(userId);
            return true;
        } else {
            if (curDate.equals(now)) {
                if (cache.contains(userId)) {
                    return false;
                } else {
                    cache.add(userId);
                    return true;
                }
            } else {//新的一天
                cache.clear();
                cache.add(userId);
                curDate = now;
                return true;
            }
        }
    }

    /**
     * 判定是否登录
     *
     * @param header
     * @return
     */
    private static boolean isLogin(final ReqHeader header) {
        Long acctime = header.getAccesstime();
        if (acctime != null) {
            long acc = acctime.longValue();
            long now = System.currentTimeMillis();
            if (now > acc + def_expire) {
                return false;
            }
        }
        Long uid = header.getUid();
        String token = header.getToken();
        Object oldToken = CacheUtils.get(Constants.API_LOGIN_KEY + header.getUid());
        if (uid == null || uid.compareTo(0L) == 0 || StringUtils.isBlank(token) || oldToken == null) {
            return false;
        }
        final String ot = String.valueOf(oldToken);
        if (StringUtils.isBlank(ot)) {
            return false;
        }
        if (!ot.equals(token)) {
            return false;
        }
        return true;
    }
}
