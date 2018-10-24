/**
 *
 */
package com.empathy.ex;

/**
 * @author tybest
 * @date 2017年6月15日 下午2:11:14
 * @email tybest@126.com
 * @desc
 */
public class NotLoginException extends BizBaseException {

    /**
     * @desc
     */
    private static final long serialVersionUID = 7709965249608047518L;

    public NotLoginException() {
        super();
    }

    public NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }
}
