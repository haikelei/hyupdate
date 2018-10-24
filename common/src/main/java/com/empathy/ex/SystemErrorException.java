/**
 *
 */
package com.empathy.ex;

/**
 * @author tybest
 * @date 2017年6月15日 下午2:10:44
 * @email tybest@126.com
 * @desc
 */
public class SystemErrorException extends BizBaseException {

    /**
     * @desc
     */
    private static final long serialVersionUID = -93178715121748801L;

    public SystemErrorException() {
        super();
    }

    public SystemErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SystemErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemErrorException(String message) {
        super(message);
    }

    public SystemErrorException(Throwable cause) {
        super(cause);
    }
}
