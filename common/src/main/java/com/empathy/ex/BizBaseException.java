/**
 *
 */
package com.empathy.ex;

/**
 * @author tybest
 * @date 2017年6月15日 下午2:08:04
 * @email tybest@126.com
 * @desc
 */
public class BizBaseException extends RuntimeException {

    /**
     * @desc
     */
    private static final long serialVersionUID = 5357744996159562805L;

    public BizBaseException() {
        super();
    }

    public BizBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BizBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizBaseException(String message) {
        super(message);
    }

    public BizBaseException(Throwable cause) {
        super(cause);
    }
}
