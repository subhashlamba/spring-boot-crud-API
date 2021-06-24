package com.prominentpixel.angularexcercise.exception;

/**
 * Custom exception
 */
public class PPException extends RuntimeException {
    private static final long serialVersionUID = 8671029394341354094L;

    private Integer httpStatusCode = 500;
    private String errMsg;
    private boolean printLog = true;

    /**
     * @param message
     */
    public PPException(String errMsg) {
        super(errMsg);
        setErrMsg(errMsg);
    }

    /**
     * @param message
     * @param cause
     */
    public PPException(String message, Throwable cause) {
        super(message, cause);
        setErrMsg(errMsg);
    }

    @Override
    public String getLocalizedMessage() {
        return getHttpStatusCode() + ": " + super.getLocalizedMessage();
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @return current instance for chaining
     */
    public PPException setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @return current instance for chaining
     */
    public PPException setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public boolean isPrintLog() {
        return printLog;
    }

    /**
     * @return current instance for chaining
     */
    public PPException setPrintLog(boolean printLog) {
        this.printLog = printLog;
        return this;
    }

}
