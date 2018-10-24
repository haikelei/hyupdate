package com.empathy.service;

import com.empathy.common.RspResult;
import com.empathy.ex.BizBaseException;
import com.empathy.task.EventNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @author tybest
 * @date 2017/8/5 8:44
 * @email zhoujian699@126.com
 * @desc
 **/
public abstract class AbstractBaseService {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractBaseService.class);

    /**
     * 成功
     *
     * @param total
     * @param rows
     * @return
     */
    protected RspResult success(long total, Object rows) {
        return new RspResult(200, null, total, rows);
    }

    protected RspResult success() {
        return new RspResult();
    }

    protected RspResult success(Object rows) {
        return new RspResult(200, "操作成功", 0L, rows);
    }

    protected RspResult error(int code, String msg) {
        return new RspResult(code, msg, 0L, null);
    }

    protected RspResult errorMo() {
        return new RspResult(1, "id绑定类型不存在", 0L, null);
    }
    protected RspResult errorNo() {
        return new RspResult(1, "操作失败！", 0L, null);
    }

    protected RspResult bizError(String msg) {
        return new RspResult(502, msg, 0L, null);
    }

    protected RspResult error(String msg) {
        return new RspResult(500, msg, 0L, null);
    }

    protected void throwBizEx(String message) {
        throw new BizBaseException(message);
    }


    protected Date getNow() {
        return Calendar.getInstance().getTime();
    }


    protected EventNode buildNode(Long userId, Long targetId, int type, String content, Long referId) {
        EventNode node = new EventNode();
        node.setUserId(userId);
        node.setTargetId(targetId);
        node.setType(type);
        node.setContent(content);
        node.setReferId(referId);
        return node;
    }

    /**
     * @param begin
     * @param end
     * @param now
     * @return
     */
    protected boolean betweenRange(Date begin, Date end, Date now) {
        if (begin == null || end == null) {
            return false;
        }
        return begin.before(now) && end.after(now);
    }
}
